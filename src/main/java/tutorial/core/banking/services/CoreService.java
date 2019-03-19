package tutorial.core.banking.services;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;

import tutorial.core.banking.infrastructure.IMessageSender;
import tutorial.core.banking.models.Account;
import tutorial.core.banking.models.AccountType;
import tutorial.core.banking.models.TransferStatus;

public class CoreService {

	private	static final org.apache.logging.log4j.Logger logger = LogManager.getRootLogger(); // singleton pattern - code smell!!!

	public CoreService(){
	
	}
	
	public TransferStatus TransferMoneyToAnotherAccount(double amount, Account fromAccount,Account toAccount) throws Exception {
					
		logger.debug("Started: TransferMoneyToAnotherAccount");
		
		if(amount<=0) {
			//logger.Log(LocalDateTime.now() + "amount should be greater than zero");
			throw new InvalidParameterException("amount should be greater than zero");
		}
				
		if(fromAccount == null)
			throw new InvalidParameterException("account should not be null");
	
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
			return TransferStatus.Fraud;			
		}
			
		double toNewBalanace = toAccount.getBalance()+amount;
		toAccount.setBalance(toNewBalanace);
		
		double fromNewBalanace = fromAccount.getBalance() - amount;
		fromAccount.setBalance(fromNewBalanace);
		
		logger.debug("Finished: TransferMoneyToAnotherAccount");
		
		return TransferStatus.Valid;
	}


	public boolean IsThisAFraudTransfer(double amount, Account account) {
		
		// Use some fancy machine learning techniques to detect fraud transfers.
		// however, here we have a super simple way to check it.
		
		if(amount>8000)
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
