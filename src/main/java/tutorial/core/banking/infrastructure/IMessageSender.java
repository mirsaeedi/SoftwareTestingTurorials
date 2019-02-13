package tutorial.core.banking.infrastructure;

import java.net.ConnectException;

public interface IMessageSender {

	public void SendMessage(String address, String subject, String body) throws ConnectException;
	
}
