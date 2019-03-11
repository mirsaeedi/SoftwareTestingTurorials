import java.util.concurrent.TimeUnit;

import tutorial.core.banking.consistency.ConsitencyChecker;
import tutorial.core.banking.models.User;

public class Startup {

	public static void main(String[] args) throws InterruptedException {
	    
		RunConsistencyChecking();
	}
	
    public static void RunConsistencyChecking() throws InterruptedException {
    	
    	// first you need to instantiate consitencyChecker with correct parameters
    	ConsitencyChecker<User> consitencyChecker = null;
    
    	while(true) {
    		
    		consitencyChecker.check();
    		
    		// here we just simulate
			TimeUnit.SECONDS.sleep(1);
    	}
    	
    }
}
