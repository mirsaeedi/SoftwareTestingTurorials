package tutorial.core.banking.services;

import java.net.SocketException;

import tutorial.core.banking.infrastructure.ExternalDependency;
import tutorial.core.banking.models.Account;
import tutorial.core.banking.models.TransferStatus;

/*
 *  This is an external Dependency
 */

public class InterBankingService implements ExternalDependency {

	
	public TransferStatus Interac(double amount, Account from, String phoneNumber) throws SocketException {
		
		throw new SocketException
		("we can not call this method in our dev enviroment because we are no connected to Interac network");
		
	}

}
