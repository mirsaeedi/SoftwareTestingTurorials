package tutorial.core.banking.features;

import tutorial.core.banking.infrastructure.EmailSender;
import tutorial.core.banking.models.Account;

public class EmailSendingFeature implements BaseMessagingFeature{

	@Override
	public void SendMessage(Account account) {
		
		EmailSender emailSender=new EmailSender();
		emailSender.SendMessage(account.getEmail(),"Successful Transaction", "Thank you for using our service!");			
	}

}
