package tutorial.core.banking.data;

import java.util.Random;
import tutorial.core.banking.models.Account;

/*
 *  This is an external Dependency
 */
public class DataRepository {

	private static Random rnd=new Random();
	
	public void SetBalanceOfAccount(String accountNumber, double amount) {
		
		throw new UnsupportedOperationException("this feature is not implemented yet. another team is working on it"); 
	}

	public Account getAccountByAccountNumber(String accountNumber) {
		throw new UnsupportedOperationException("this feature is not implemented yet. another team is working on it"); 
	}

	public void saveAccount(Account fromAccount) throws Exception {
		
		// this method connects to db which is sloooow and non deterministic
		
		int luck = rnd.nextInt(3);
	
		if(luck==1) {
			throw new Exception("Timeout");
		}
		
		if(luck==2) {
			throw new Exception("Handshake Error");
		}
		
		if(luck==3) {
			throw new Exception("Concurrency error on write");
		}
	}

}