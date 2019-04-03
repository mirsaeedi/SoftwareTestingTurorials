package tutorial.core.banking.services;
import java.security.InvalidParameterException;
import tutorial.core.banking.infrastructure.EmailSender;
import tutorial.core.banking.infrastructure.TextSender;
import tutorial.core.banking.models.Account;
import tutorial.core.banking.models.TransferStatus;

public class CoreService {

	private EmailSender emailSender;
	private TextSender textSender;
	
	public CoreService(){
	
		textSender = new TextSender();
		emailSender = new EmailSender();
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
