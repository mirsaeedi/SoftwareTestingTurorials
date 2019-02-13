package tutorial.core.banking.infrastructure;

import java.net.ConnectException;

/*
 *  This is an external Dependency
 */

public class EmailSender implements IMessageSender {

	public void SendMessage(String address, String subject, String body) throws ConnectException {
		
		throw new ConnectException("There is no connection to mail server"); 
		
	}

}
