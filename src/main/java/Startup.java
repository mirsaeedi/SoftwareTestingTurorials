import tutorial.core.banking.models.Account;
import tutorial.core.banking.models.AccountType;
import tutorial.core.banking.services.CoreService;

public class Startup {

	public static void main(String[] args) throws InterruptedException {
	    
		Account account = new Account("12873192763812",2000,"joe@mtl.ca",AccountType.Checking);
		
		CoreService coreService = new CoreService(true,false);
		
		coreService.Deposit(100, account);
		
	}
	
}
