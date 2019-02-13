package tutorial.core.banking.configuration;

import com.google.inject.AbstractModule;

import tutorial.core.banking.data.DataRepository;
import tutorial.core.banking.infrastructure.IMessageSender;

public class BankDependenciesModule extends AbstractModule {

	@Override 
	  protected void configure() {

	    bind(DataRepository.class);
	    
	    
	}
	
}
