import tutorial.core.banking.features.BaseMessagingFeature;
import tutorial.core.banking.features.FeatureAwareFactory;
import tutorial.core.banking.models.Account;
import tutorial.core.banking.models.AccountType;
import tutorial.core.banking.services.CoreService;

public class Startup {

	public static void main(String[] args) throws InterruptedException {
	    
		Account account = new Account("12873192763812",2000,"joe@mtl.ca",AccountType.Checking);
		FeatureAwareFactory factory= new FeatureAwareFactory();
		BaseMessagingFeature feature = factory.getMessagingFeature(true, false);
	
		CoreService coreService = new CoreService(feature);
		coreService.Deposit(100, account);
	}
	
}
