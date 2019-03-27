package tutorial.core.banking.features;

import tutorial.core.banking.infrastructure.TextSender;
import tutorial.core.banking.models.Account;

public class TextSendingFeature implements BaseMessagingFeature {

	@Override
	public void SendMessage(Account account) {

		TextSender textSender=new TextSender();
		textSender.SendMessage(account.getPhoneNumber(),"Successful Transaction", "Thank you for using our service!");			
	}
}
