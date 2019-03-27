package tutorial.core.banking.infrastructure;

public class TextSender implements IMessageSender {

	public void SendMessage(String address, String subject, String body) {
		
		 System.out.println("Text Sent to " + address);
		
	}

}