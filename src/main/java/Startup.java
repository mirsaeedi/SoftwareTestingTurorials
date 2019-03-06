import com.google.inject.Guice;
import com.google.inject.Injector;

import tutorial.core.banking.configuration.BankDependenciesModule;
import tutorial.core.banking.data.DataRepository;
import tutorial.core.banking.services.CoreService;

public class Startup {

	public static void main(String[] args) {
	    
		
	    Injector injector = Guice.createInjector(new BankDependenciesModule());
	    CoreService coreService = injector.getInstance(CoreService.class);
	    
	}

}
