import com.google.inject.Guice;
import com.google.inject.Injector;

import tutorial.core.banking.configuration.BankDependenciesModule;
import tutorial.core.banking.data.DataRepository;
import tutorial.core.banking.services.CoreService;

public class Startup {

	public static void main(String[] args) {
	    
		/*
		 * Tasks:
		 *  1. Make dependency to EmailSender explicit
		 *  2. Follow DIP principle to replace EmailSender with IMessageSender
		 *  3. Make dependency to FileLogger explicit
		 *  4. Follow DIP to have a ILogger instead of the FileLogger as dependency
		 *  5. Tell Guice to inject a Singleton instance of ILogger
		 *  
	     * Guice.createInjector() takes your Modules, and returns an Injector which knows how to instantiate objects 
	     */
		
	    //Injector injector = Guice.createInjector(new BankDependenciesModule());
	    //CoreService x = injector.getInstance(CoreService.class);
	    
	    DataRepository dataRepository = new DataRepository();
	    CoreService coreService = new CoreService(dataRepository);
	}

}
