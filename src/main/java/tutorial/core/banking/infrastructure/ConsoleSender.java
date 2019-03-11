package tutorial.core.banking.infrastructure;

import java.net.ConnectException;

public class ConsoleSender implements IMessageSender {

	public void SendMessage(String address, String subject, String body) throws ConnectException {
		
		System.out.println(body);		
	}

}
