package tutoria.core.banking.transfer.test;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.net.ConnectException;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;

import tutorial.core.banking.data.DataRepository;
import tutorial.core.banking.infrastructure.EmailSender;
import tutorial.core.banking.infrastructure.FileBasedLogger;
import tutorial.core.banking.infrastructure.TimeManager;
import tutorial.core.banking.models.Account;
import tutorial.core.banking.models.AccountType;
import tutorial.core.banking.models.TransferStatus;
import tutorial.core.banking.services.CoreService;
import tutorial.core.banking.services.InterBankingService;

/*
 * We want to test every corner of our implemented business logic
 * to assure ourselves that it works as expected
 */

public class TestTransferScenarios {
	
	private CoreService bankingCoreService;

	/*.
	 * In this tutorial, the production code has external dependencies to an Email Sender, Database, File System, Time.
	 * Since in unit test we want to test the behavior and logic of the code, if need to isolate the code from external dependencies.
	 * Therefore, our tests will become fast and deterministic. So, you should:
	 * 		1. Make the production code testable. (As of now, the code is not testable because its dependencies are hidden inside
	 * its implementation. In other words, the dependencies are not mockable and we can not test the code) 
	 * 		2. In tests you have to mock the dependencies.
	 * 		3. Make all the tests to pass.
	 */
	
	/*
	 * Scenario (Requirement): When a money transfer is detected as a fraud transaction,
	 * we should send an email to the security team ("security_team@rbc.ca") with subject "fraud".
	 * 
	 * 
	 * Tasks: Fix the bugs inside the test code.
	 */
	
	@Test
	public void IfMoneyTransferIsFraud_SendEmailToSecurity() throws Exception {
		 
		//arrange
		
		double fromBalance=20000;
		Account from= new Account("accountNumber1",fromBalance,false,AccountType.Checking);

		double toBalance=500;
		Account to= new Account("accountNumber2",toBalance,false,AccountType.Checking);

		// an amount greater than 1K $ leads to a fraud transfer
		double transferAmount= 10001;
		
		// here we mock the external dependencies in order to make the test fast, deterministic, and reliable
        // EmailSender emailSender = new EmailSender(); // TODO FIX: instead, mock the email sender, 
															// Mockito can do that only if the constructor takes an emailSender object as dependency, 
															// which it doesn't in the original code)
		EmailSender emailSender = mock(EmailSender.class);
        DataRepository dataRepository = mock(DataRepository.class); 
        // TODO: after fixing the next tests:
        TimeManager timeManager = mock(TimeManager.class);
        FileBasedLogger fileBasedLogger = mock(FileBasedLogger.class);


        // after creating mocks, we define their behavior. In other words, we stub them.
        when(dataRepository.getAccountByAccountNumber("accountNumber1")).thenReturn(from);
        when(dataRepository.getAccountByAccountNumber("accountNumber2")).thenReturn(to);

		this.bankingCoreService =  new CoreService (emailSender,dataRepository, fileBasedLogger, timeManager); // TODO FIX: add new dependencies as params
		
		//act
		
		TransferStatus transferStatus= bankingCoreService.TransferMoneyToAnotherAccount(transferAmount, "accountNumber1", "accountNumber2");
		
		//assert
		
		assertThat(transferStatus,is(TransferStatus.Fraud));
		
		// using verify() method, we make sure that the collaboration between collaborators is based on the defined requirements.
	    //verify(emailSender).SendEmail("secuityteam@nationalBank.ca", "Hello", anyString()); // TODO FIX: change the params, the code will not send these params by email ...
		verify(emailSender).SendEmail("secuityteam@rbc.ca", "fraud", "Hi Guys! Something here does not seem good :D");

	}
	
	/*
	 * Scenario (Requirement): When a deposit into an account is detected as a fraud transaction
	 * we should send an email to the security team ("security_team@rbc.ca") with subject "fraud".
	 * 
	 * Taks: Fill the blanks :D.
	 */
	
	@Test
	public void IfMoneyDepositIsFraud_SendEmailToSecurity() throws ConnectException, Exception {
		 
		//arrange
		
		double fromBalance=20000;
		Account from= new Account("accountNumber1",fromBalance,false,AccountType.Checking);
		
		// an amount greater than 10K $ leads to a fraud transfer
		double transferAmount= 10001;
		
		// next do mocking and stubbing
		EmailSender emailSender = mock(EmailSender.class);
		DataRepository dataRepository = mock(DataRepository.class);
		// TODO: to add after fixing the next tests: 
		FileBasedLogger fileBasedLogger = mock(FileBasedLogger.class);
		TimeManager timeManager = mock(TimeManager.class);
		
        when(dataRepository.getAccountByAccountNumber("accountNumber1")).thenReturn(from);
        
        this.bankingCoreService =  new CoreService (emailSender,dataRepository, fileBasedLogger, timeManager); // TODO FIX: add new dependencies as params
		
		//act
        TransferStatus transferStatus = null;
		transferStatus= bankingCoreService.Deposit(transferAmount, "accountNumber1");		
		
		//assert
        assertThat(transferStatus,is(TransferStatus.Fraud));
        verify(emailSender).SendEmail("secuityteam@rbc.ca", "fraud", "Hi Guys! Something here does not seem good :D");

	}
	
	/*
	 * Scenario (Requirement): The amount parameter of Deposit operation should be greater than zero, otherwise 
	 * InvalidParameterException exception would be thrown
	 * 
	 * 
	 * Task: make the production code testable. It's now dependent on File System (its logger).
	 * Then, mock all the dependencies.
	 */
	
	@Test(expected = InvalidParameterException.class)
	public void NegativeAmountInMoneyDeposit_IsNotAllowed() throws Exception {
		
		 
		//arrange
		double balance=1000;
		Account account= new Account("accountNumber1",balance,false,AccountType.Checking);
		double amount= -100;

		// here we mock the external dependencies in order to make the test fast, deterministic, and reliable
        // EmailSender emailSender = new EmailSender();
		EmailSender emailSender = mock(EmailSender.class);
        DataRepository dataRepository = mock(DataRepository.class);
        
		// TODO FIX: make sure you made the logger as a explicit dependency, injected into the constructor of CoreService:
		// TODO: and fix all constructors across the codebase.
        // TODO FIX: mock it (and then the test below is correct)
        FileBasedLogger fileBasedLogger = mock(FileBasedLogger.class);
        
        // TODO: after fixing the next tests:
        TimeManager timeManager = mock(TimeManager.class);
        
        when(dataRepository.getAccountByAccountNumber("accountNumber2")).thenReturn(account);
        
		this.bankingCoreService =  new CoreService (emailSender,dataRepository, fileBasedLogger, timeManager); // TODO FIX: add logger.

		//act
		
		TransferStatus transferStatus= bankingCoreService.Deposit(amount, "accountNumber1"); // should throw an exception for the test to pass, see @Test annotation above
		
		//assert
		
		// OPTIONAL: you can verify the call to the logger if you want, 
		// but since it's not an important part of your business logic, 
		// you can just mock it for the purpose of not really writing to disk during the tests
		// verify(fileBasedLogger).Log(LocalDateTime.now() + "amount should be greater than zero");
		
		assertThat(account.getBalance(),is(balance)); // check that balance is unchanged
		
		
		
	}
	
	/*
	 * Scenario (Requirement): Deposits into TFSA accounts should stay within an annually limit. 
	 * Canada Revenue Agency defines the yearly limits.
	 * 1. the limit for 2017 is 5500 $
	 * 1. the limit for 2018 is 5500 $
	 * 1. the limit for 2019 is 6000 $
	 * 
	 * Task: make sure the code behaves well just for the year 2017.
	 * The production code is coupled with time. So, you have to refactor the code to decouple it from time. 
	 */
	@Test
	public void DeposistsIntoTFSAAccountsWhichIsGreaterThanLimits_IsNotAllowed() throws Exception {
				
		//arrange
		double balance=1000;
		Account account= new Account("accountNumber1",balance,false,AccountType.TFSA);
		double amount= 5501;
		        
        EmailSender emailSender = mock(EmailSender.class);
        DataRepository dataRepository = mock(DataRepository.class);
        FileBasedLogger fileBasedLogger = mock(FileBasedLogger.class);
        // TODO FIX: this test will require the Time dependency 
        // ... why? because we need to call the getCurrentTime function for any year, (here for the year 2017 that we're testing) 
        // for example, after creating a TimeManager object in the code, use it this way:
        TimeManager timeManager = mock(TimeManager.class);
        when(timeManager.getCurrentYear()).thenReturn(2017);
        // TODO: also stub the dataRepository class:
        when(dataRepository.getAccountByAccountNumber("accountNumber1")).thenReturn(account);
        
		this.bankingCoreService =  new CoreService (emailSender,dataRepository, fileBasedLogger, timeManager);// TODO FIX: add new dependencies as params

		//act
		
		TransferStatus transferStatus= bankingCoreService.Deposit(amount, "accountNumber1");
		
		//assert
		assertThat(account.getBalance(),is(balance));
	}
	
	
	/*
	 * Scenario (Requirement): When depositing money, if the database is not accessible to our application, 
	 * we have to inform the DevOps Team via an email to devops@rbc.com. The database is considered as not accessible when
	 * accessing it causes java.net.ConnectException exception
	 * 
	 * Task: There is a bug in test code. And Also you need to fill the assert section to verify the behavior of collaborators
	 */
	@Test 
	public void InCaseOfDatabaseFailing_SendEmailToDevOps()  throws Exception{
		 
		//arrange
		double balance=1000;
		Account account= new Account("accountNumber1",balance,false,AccountType.TFSA);
		double amount= 5001;
		
        EmailSender emailSender = mock(EmailSender.class);
        DataRepository dataRepository = mock(DataRepository.class);
        FileBasedLogger fileBasedLogger = mock(FileBasedLogger.class);
        TimeManager timeManager = mock(TimeManager.class);
        
        // NOTE: here we can have our mock throw an exception if we want to:
        // when(dataRepository.getAccountByAccountNumber("accountNumber2")).thenThrow(new InvalidParameterException()); 
        // TODO: change to the correct exception: and also it's accountNumber1:
        when(dataRepository.getAccountByAccountNumber("accountNumber1")).thenThrow(new ConnectException());
        
		this.bankingCoreService =  new CoreService (emailSender,dataRepository, fileBasedLogger, timeManager); // TODO FIX: add new dependencies as params

		//act
		
		TransferStatus transferStatus= bankingCoreService.Deposit(amount, "accountNumber1");
		
		//assert
		verify(emailSender).SendEmail("devops@rbc.com", "db is down", "fix it asap.");
		
		
		
	}
	
}
