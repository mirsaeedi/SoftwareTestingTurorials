package tutoria.core.banking.transfer.test;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.net.ConnectException;
import java.security.InvalidParameterException;

import tutorial.core.banking.data.DataRepository;
import tutorial.core.banking.infrastructure.EmailSender;
import tutorial.core.banking.models.Account;
import tutorial.core.banking.models.AccountType;
import tutorial.core.banking.models.TransferStatus;
import tutorial.core.banking.services.CoreService;
import tutorial.core.banking.services.InterBankingService;

/*
 * We want to test every corner of our implemented business logic
 * to assure ourselves that it works as expected
 */


@RunWith(MockitoJUnitRunner.class)
public class TestTransferScenarios {
	
	@InjectMocks
	private CoreService bankingCoreService;

	
	/*
	 * Scenario (Requirement): When a money transfer is detected as a fraud transaction,
	 * we should send an email to the security team ("security_team@rbc.ca") with subject "fraud".
	 * 
	 * 
	 * Tasks: Test Code should not be coupled with the production code.
	 * Chagnes in production code should not force you to change all your test codes
	 */
	
	@Test
	public void IfMoneyDepositIsFraud_SendEmailToSecurity() throws Exception {
		 
		//arrange
		
		double fromBalance=20000;
		Account from= new Account("accountNumber1",fromBalance,false,AccountType.Checking);

		// an amount greater than 1K $ leads to a fraud transfer
		double transferAmount= 10001;
		
		// here we mock the external dependencies in order to make the test fast, deterministic, and reliable
        DataRepository dataRepository = mock(DataRepository.class);

        // after creating mocks, we define their behavior. In other words, we stub them.
        when(dataRepository.getAccountByAccountNumber("accountNumber1")).thenReturn(from);

		this.bankingCoreService =  new CoreService (dataRepository);
		
		//act
		
		TransferStatus transferStatus= bankingCoreService.Deposit(transferAmount, "accountNumber1");
		
		//assert
				
		

	}	
	
}
