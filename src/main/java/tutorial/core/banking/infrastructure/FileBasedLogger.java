package tutorial.core.banking.infrastructure;

import java.util.Random;

public class FileBasedLogger implements ExternalDependency {
	
	private static Random rnd=new Random();
	
	public static void Log(String message) throws Exception {
		
		// this logger writes to file system which is sloooow and non deterministic
		
		int luck = rnd.nextInt(3);
	
		if(luck==0) {
			throw new Exception("Not Enough Disk Space");
		}
		
		if(luck==1) {
			throw new Exception("You have not access to this file");
		}
		
		if(luck==2) {
			throw new Exception("File does not exist");
		}
		
	}

}
