package tutorial.core.banking.infrastructure;

public class EmailSender implements IMessageSender {

	public void SendMessage(String address, String subject, String body) {
		
		 System.out.println("Email Sent to " + address);
		
	}

}