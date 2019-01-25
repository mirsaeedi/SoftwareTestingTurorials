package tutorial.core.banking.services;

import java.net.ConnectException;
import java.security.InvalidParameterException;

import tutorial.core.banking.data.DataRepository;
import tutorial.core.banking.infrastructure.EmailSender;
import tutorial.core.banking.models.Account;
import tutorial.core.banking.models.TransferStatus;

public class CoreService {


	public CoreService(EmailSender emailSender, DataRepository dataRepository, InterBankingService interBankingService){
		
	}
	
	public TransferStatus TransferMoneyToAnotherAccount(double amount, Account fromAccount, Account toAccount) throws ConnectException {
					
		if(amount<=0)
			throw new InvalidParameterException("amount should be greater than zero");
		
		if(fromAccount == null)
			throw new InvalidParameterException("account should not be null");
		
		if(toAccount == null)
			throw new InvalidParameterException("account should not be null");
		
		if(fromAccount.getAccountNumber()==toAccount.getAccountNumber()) {
			// return TransferStatus.Valid;
			return TransferStatus.Fraud; // FIX: this should be invalid / Fraud. 
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
		// FIX: TransferMoneyFromANormalAccountToABlockedAccount_IsNotAllowed()
		if(IsAccountBlocked(toAccount)) {
			return TransferStatus.AccountIsBlockedError;
		}
			
		if(IsThisAFraudTransfer(amount,fromAccount)) {
			fromAccount.setIsBlocked(true);
			toAccount.setIsBlocked(true);
			return TransferStatus.Fraud;			
		}
			
		double toNewBalanace = toAccount.getBalance()+amount;
		toAccount.setBalance(toNewBalanace);
		
		// double fromNewBalanace = fromAccount.getBalance()+amount;
		double fromNewBalanace = fromAccount.getBalance()-amount; // FIX: -amount .. for test: TransferMoneyFromOneNormalAccountToAnotherNormalAccount_IsOk()
		fromAccount.setBalance(fromNewBalanace);
		
		return TransferStatus.Valid;
	}


	public TransferStatus Deposit(double amount,  Account account) throws ConnectException {
				
		if(amount<=0)
			throw new InvalidParameterException("amount should be greater than zero");
		
		// FIX: DepositThatExceedsTheMaximumAllowedBalance_IsNotAllowed
		if(DoesItExceedTheMaximumAllowedBalance(amount, account)) {
			return TransferStatus.MaximumAllowedBalanceExceededError;
		}
		
		if(account == null)
			throw new InvalidParameterException("account should not be null");
		
		if(IsAccountBlocked(account)) {
			return TransferStatus.AccountIsBlockedError;
		}
		
		if(IsThisAFraudTransfer(amount,account)) {
			account.setIsBlocked(true);
			return TransferStatus.Fraud;
		}
		
		double newBalanace = account.getBalance()+amount;
		account.setBalance(newBalanace);
		
		return TransferStatus.Valid;
	}

	
	public TransferStatus Withdrawal(double amount,  Account account) throws ConnectException {
		
		// FIX: WithdrawalWithNegativeAmount_IsNotAllowed()
		if(amount<=0)
			throw new InvalidParameterException("amount should be greater than zero");
		
		
		if(account.getBalance()<amount)
			return TransferStatus.NotEnoughMoneyError;
		
		if(IsAccountBlocked(account)) {
			return TransferStatus.AccountIsBlockedError;
		}
		
		if(IsThisAFraudTransfer(amount,account)) {
			account.setIsBlocked(true);
			return TransferStatus.Fraud;
		}
		
		double newBalanace = account.getBalance()-amount;
		account.setBalance(newBalanace);
		
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
