package tutorial.web;

import org.apache.logging.log4j.ThreadContext;

import tutorial.core.banking.models.Account;
import tutorial.core.banking.models.AccountType;
import tutorial.core.banking.services.CoreService;

public class AccountController {
	 
	public AccountController(HttpSession httpSession){
		
		ThreadContext.put("ipAddress", httpSession.IpAddress);
		
	}
    
    public void TransferMoney() {
    	
    	Account fromAccount = new Account("from_account_number",2000,false,AccountType.Checking);
		Account toAccount = new Account("to_account_number",5000,false,AccountType.Checking);
		
		CoreService coreService = new CoreService();
		
		try {
			coreService.TransferMoneyToAnotherAccount(100, fromAccount, toAccount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}