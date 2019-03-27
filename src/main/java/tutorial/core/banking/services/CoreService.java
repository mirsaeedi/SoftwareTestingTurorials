package tutorial.core.banking.services;
import java.security.InvalidParameterException;
import java.util.ArrayList;

import tutorial.core.banking.features.BaseMessagingFeature;
import tutorial.core.banking.features.FeatureAwareFactory;
import tutorial.core.banking.models.Account;
import tutorial.core.banking.models.TransferStatus;

public class CoreService {

	FeatureAwareFactory featureAwareFactory;

	public CoreService(FeatureAwareFactory featureAwareFactory){
		this.featureAwareFactory = featureAwareFactory;
	}
	
	public TransferStatus Deposit(double amount,  Account account) {
				
		if(amount<=0) {
			throw new InvalidParameterException("amount should be greater than zero");
		}
		
		if(account == null) {
			throw new InvalidParameterException("account should not be null");
		}
				
		if(IsAccountBlocked(account)) {
			return TransferStatus.AccountIsBlockedError;
		}
	
		if(IsThisAFraudTransfer(amount,account)) {
			account.setIsBlocked(true);
			return TransferStatus.Fraud;
		}
		
		double newBalanace = account.getBalance()+amount;
		account.setBalance(newBalanace);

		SendMessage(account);
	
		return TransferStatus.Valid;
	}


	private void SendMessage(Account account) {
		
		ArrayList<BaseMessagingFeature> features = featureAwareFactory.getMessagingFeature();
		
		for(BaseMessagingFeature feature:features) {
			feature.SendMessage(account);
		}
	}

	public TransferStatus Withdrawal(double amount,Account account) {
		
		if(amount<=0) {
			throw new InvalidParameterException("amount should be greater than zero");
		}
	
		if(account == null) {

			throw new InvalidParameterException("account should not be null");
		}
		
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
		
		SendMessage(account);
		
		return TransferStatus.Valid;
	}

	private boolean IsThisAFraudTransfer(double amount, Account account) {
		
		// Use some fancy machine learning techniques to detect fraud transfers.
		// however, here we have a super simple way to check it.
		
		if(amount>10000)
			return true;
		
		return false;
	}
	
	private boolean IsAccountBlocked(Account account) {
		return account.getIsBlocked();
	}

	
}