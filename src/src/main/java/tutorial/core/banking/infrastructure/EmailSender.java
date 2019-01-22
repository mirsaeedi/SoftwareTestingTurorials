package tutorial.core.banking.infrastructure;

import java.net.ConnectException;

/*
 *  This is an external Dependency
 */

public class EmailSender implements ExternalDependency {

	public void SendEmail(String emailAddress, String mailSubject, String mailBody) throws ConnectException {
		
		throw new ConnectException("There is no connection to mail server"); 
		
	}

}
