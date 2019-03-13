import java.util.concurrent.TimeUnit;

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
