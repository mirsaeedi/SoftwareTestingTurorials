import java.util.concurrent.TimeUnit;

import tutorial.core.banking.consistency.ConsitencyChecker;
import tutorial.core.banking.consistency.IRule;
import tutorial.core.banking.consistency.TotalBalanceRule;
import tutorial.core.banking.data.UserRepository;
import tutorial.core.banking.infrastructure.ConsoleSender;
import tutorial.core.banking.models.User;

public class Startup {

	public static void main(String[] args) throws InterruptedException {
	    
		RunConsistencyChecking();
	}
	
    public static void RunConsistencyChecking() throws InterruptedException {
    	
    	// first you need to instantiate consitencyChecker with correct parameters
    
    	while(true) {
    		
    		// run consistenctCheck method of ConsistencyChecker here
    		
    		// here we just simulate
			TimeUnit.SECONDS.sleep(1);
    	}
    	
    }
}
