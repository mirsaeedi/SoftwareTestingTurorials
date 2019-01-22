package tutorial.core.banking.data;

import tutorial.core.banking.infrastructure.ExternalDependency;

/*
 *  This is an external Dependency
 */
public class DataRepository implements ExternalDependency {

	public double GetBalanceOfAccount(String accountNumber) {
	
		// this method reads the balance amount from the database.
		// this operation is very slow and resource consuming. 
		// Using the actual implementation in unit test is not recommended,
		// because we want our unit tests to run very fast. 
		// This way we can test our software without losing any extra seconds
		
		return 0;
	}

	public void SetBalanceOfAccount(String accountNumber, double amount) {
		
		throw new UnsupportedOperationException("this feature is not implemented yet. another team is working on it"); 
	}

}
