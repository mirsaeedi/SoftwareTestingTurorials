package tutorial.core.banking.services;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.Calendar;

import tutorial.core.banking.data.DataRepository;
import tutorial.core.banking.infrastructure.EmailSender;
import tutorial.core.banking.infrastructure.FileBasedLogger;
import tutorial.core.banking.models.Account;
import tutorial.core.banking.models.AccountType;
import tutorial.core.banking.models.TransferStatus;

public class CoreService {

	private EmailSender emailSender;
	private DataRepository dataRepository;

	public CoreService(DataRepository dataRepository){
	
		this.emailSender= new EmailSender();
		this.dataRepository=dataRepository;
	}
	
	public TransferStatus TransferMoneyToAnotherAccount(double amount, String fromAccountNumber,String toAccountNumber) throws Exception {
			
		if(amount<=0) {
			FileBasedLogger.Log(LocalDateTime.now() + "amount should be greater than zero");
			throw new InvalidParameterException("amount should be greater than zero");
		}
			
		Account fromAccount = dataRepository.getAccountByAccountNumber(fromAccountNumber);
		
		if(fromAccount == null)
			throw new InvalidParameterException("account should not be null");
		
		Account toAccount = dataRepository.getAccountByAccountNumber(toAccountNumber);
		
		if(toAccount == null)
			throw new InvalidParameterException("account should not be null");
		
		if(fromAccount.getAccountNumber()==toAccount.getAccountNumber()) {
			return TransferStatus.Valid;
		}
		
		if(DoesItExceedTheMaximumAllowedBalance(amount,toAccount)) {
			return TransferStatus.MaximumAllowedBalanceExceededError;
		}
		
		if(!DoesItHaveEnoughMoneyToTransfer(fromAccount,amount)) {
			return TransferStatus.NotEnoughMoneyError;
		}
		
		if(IsAccountBlocked(fromAccount)) {
			return TransferStatus.AccountIsBlockedError;
		}
		
		if(IsAccountBlocked(toAccount)) {
			return TransferStatus.AccountIsBlockedError;
		}
			
		if(IsThisAFraudTransfer(amount,fromAccount)) {
			fromAccount.setIsBlocked(true);
			toAccount.setIsBlocked(true);
			emailSender.SendMessage("security_team@rbc.ca", "fraud", "Hi Guys! Something here does not seem good :D");
			return TransferStatus.Fraud;			
		}
			
		double toNewBalanace = toAccount.getBalance()+amount;
		toAccount.setBalance(toNewBalanace);
		
		double fromNewBalanace = fromAccount.getBalance() - amount;
		fromAccount.setBalance(fromNewBalanace);
		
		dataRepository.saveAccount(fromAccount);
		dataRepository.saveAccount(toAccount);
		
		return TransferStatus.Valid;
	}


	public TransferStatus Deposit(double amount,  String accountNumber) throws Exception {
				
		if(amount<=0) {
			FileBasedLogger.Log(LocalDateTime.now() + "amount should be greater than zero");
			throw new InvalidParameterException("amount should be greater than zero");
		}
		
		try {
			
			Account account = dataRepository.getAccountByAccountNumber(accountNumber);
		
			if(account == null) {
				FileBasedLogger.Log(LocalDateTime.now() + "account should not be null");
				throw new InvalidParameterException("account should not be null");
			}
					
			if(IsAccountBlocked(account)) {
				return TransferStatus.AccountIsBlockedError;
			}
		
			if(IsThisAFraudTransfer(amount,account)) {
				account.setIsBlocked(true);
				emailSender.SendMessage("security_team@rbc.ca", "fraud", "Hi Guys! Something here does not seem good :D");
				return TransferStatus.Fraud;
			}
			
			// in case of a TFSA account the amount should be less than the defined annual limits 
			if(account.getType()==AccountType.TFSA) {
			
				int currentYear = Calendar.getInstance().get(Calendar.YEAR);
				
				if(currentYear==2017 && amount>5000) {
					return TransferStatus.TransferAmountIsNotValid;
				}	
				
				if(currentYear==2018 && amount>5500) {
					return TransferStatus.TransferAmountIsNotValid;
				}
				
				if(currentYear==2019 && amount>6000) {
					return TransferStatus.TransferAmountIsNotValid;
				}	
			}
		
			double newBalanace = account.getBalance()+amount;
			account.setBalance(newBalanace);
		
			dataRepository.saveAccount(account);

			return TransferStatus.Valid;
		
		}catch(java.net.ConnectException e){
			emailSender.SendMessage("devops@rbc.com", "db is down", "fix it asap.");
			return TransferStatus.Error;
		}
	}

	
	public TransferStatus Withdrawal(double amount,String accountNumber) throws Exception {
		
		if(amount<=0) {
			FileBasedLogger.Log(LocalDateTime.now() + "amount should be greater than zero");
			throw new InvalidParameterException("amount should be greater than zero");
		}
		
		Account account = dataRepository.getAccountByAccountNumber(accountNumber);
		
		if(account == null) {
			FileBasedLogger.Log(LocalDateTime.now() + "account should not be null");
			throw new InvalidParameterException("account should not be null");
		}
		
		if(account.getBalance()<amount)
			return TransferStatus.NotEnoughMoneyError;
				
		if(IsAccountBlocked(account)) {
			return TransferStatus.AccountIsBlockedError;
		}
		
		if(IsThisAFraudTransfer(amount,account)) {
			account.setIsBlocked(true);
			emailSender.SendMessage("secuityteam@rbc.ca", "fraud", "Hi Guys! Something here does not seem good :D");
			return TransferStatus.Fraud;
		}
		
		double newBalanace = account.getBalance()-amount;
		account.setBalance(newBalanace);
		
		dataRepository.saveAccount(account);
		
		return TransferStatus.Valid;
	}

	private boolean IsThisAFraudTransfer(double amount, Account account) {
		
		// Use some fancy machine learning techniques to detect fraud transfers.
		// however, here we have a super simple way to check it.
		
		if(amount>10000)
			return true;
		
		return false;
	}
	
	private boolean DoesItHaveEnoughMoneyToTransfer(Account account,double amount) {
		 return account.getBalance()>=amount;
	}
	
	private boolean IsAccountBlocked(Account account) {
		return account.getIsBlocked();
	}

	private boolean DoesItExceedTheMaximumAllowedBalance(double amount, Account account) {
		
		return account.getBalance()+amount>account.getMaxAllowedBalance();
	}
	
}
