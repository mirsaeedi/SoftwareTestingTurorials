import java.util.concurrent.TimeUnit;

import tutorial.core.banking.consistency.ConsistencyChecker;
import tutorial.core.banking.data.UserRepository;
import tutorial.core.banking.infrastructure.ConsoleSender;
import tutorial.core.banking.models.User;

public class Startup {

	public static void main(String[] args) throws InterruptedException {
	    
		// Tasks: Rules that you need to have checker for them to check the consistency of Users are:
		// 1. User's Balance value should be equal to the sum of accounts' balance values
		// 2. A user can only have exactly one TFSA and RRSP account
		// 3. The balance of an account should be always smaller or equal to maxAllowedBalance except when maxAllowedBalance=0  
		// 4. add a checksum for users (optional)
		
		RunConsistencyChecking();
	}
	
    public static void RunConsistencyChecking() throws InterruptedException {
    	
    	// first you need to instantiate consitencyChecker with correct parameters
    
    	ConsistencyChecker<User> consistencyChecker = new ConsistencyChecker(
    			new ConsoleSender(),new UserRepository(),null);
    	
    	while(true) {
    		
    		// run consistenctCheck method of ConsistencyChecker here
    		consistencyChecker.check();
    		// here we just simulate
			TimeUnit.SECONDS.sleep(1);
    	}
    	
    }
}
