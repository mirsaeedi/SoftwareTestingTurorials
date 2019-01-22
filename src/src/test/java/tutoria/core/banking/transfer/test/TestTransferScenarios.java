package tutoria.core.banking.transfer.test;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.net.ConnectException;
import java.security.InvalidParameterException;

import tutorial.core.banking.data.DataRepository;
import tutorial.core.banking.infrastructure.EmailSender;
import tutorial.core.banking.models.Account;
import tutorial.core.banking.models.TransferStatus;
import tutorial.core.banking.services.CoreService;
import tutorial.core.banking.services.InterBankingService;

/*
 * We want to test every corner of our implemented business logic
 * to assure ourselves that it works as expected
 */

public class TestTransferScenarios {
	
	private CoreService bankingCoreService;

	@Before
	public void setup() {
		
		// in the setup method, we set up the system under test (SUT) and its collaborators.
		// we do it once. So, inside the test methods we do not need to do it again and have a duplicated code all over.
		
		EmailSender emailSender = new EmailSender();
		DataRepository dataRepository = new DataRepository();
		InterBankingService interBankingService=new InterBankingService();
		
		this.bankingCoreService =  new CoreService (emailSender,dataRepository,interBankingService);
	}
	
	/*.
	 * There are some inconsistencies between these tests and the SUT (System Under Test) because developers 
	 * have not implemented the requirements in a correct way.  
	 * 
	 * 1. Run the tests and eliminate the bugs in the production code. Makes all tests go Green
	 * 2. Implement the Test method MoneyTransferThatResultsInFraud_IsNotAllowed (the last one)
	 */
	
	/*
	 * Scenario (Requirement): Transferring money from a normal account to another normal account is allowed.
	 * This transaction removes the request amount of money from the 'From Account' and adds it to the 
	 * 'To Account'
	 */
	
	@Test
	public void TransferMoneyFromOneNormalAccountToAnotherNormalAccount_IsOk() throws ConnectException {
		 
		//arrange
		
		double fromBalance=1000;
		Account from= new Account("accountNumber1",fromBalance,false);

		double toBalance=500;
		Account to= new Account("accountNumber2",toBalance,false);
		
		double transferAmount= 100;
		
		//act
		
		TransferStatus transferStatus= bankingCoreService.TransferMoneyToAnotherAccount(transferAmount, from, to);
		
		//assert
		
		assertThat(transferStatus,is(TransferStatus.Valid));
		assertThat(from.getBalance(),is(fromBalance-transferAmount));
		assertThat(to.getBalance(),is(toBalance+transferAmount));
	}
	
	/*
	 * Scenario (Requirement): Transferring money from or to blocked accounts is not allowed.
	 */

	@Test
	public void TransferMoneyFromABlockedAccountToANormalAccount_IsNotAllowed() throws ConnectException {
		 
		//arrange
		
		double fromBalance=1000;
		Account from= new Account("accountNumber1",1000,false);
		
		double toBalance=500;
		Account to= new Account("accountNumber2",500,true);
		
		double transferAmount= 100;
		
		//act
		
		TransferStatus transferStatus= bankingCoreService.TransferMoneyToAnotherAccount(transferAmount, from, to);
		
		//assert
		
		assertThat(transferStatus,is(TransferStatus.AccountIsBlockedError));
		assertThat(from.getBalance(),is(fromBalance));
		assertThat(to.getBalance(),is(toBalance));
	}
	
	/*
	 * Scenario (Requirement): Transferring money from an account to itself is considered as fraud.
	 */

	@Test
	public void TransferMoneyToTheSameAccount_IsNotAllowed() throws ConnectException {
		 
		//arrange
		
		double fromBalance=1000;
		Account from= new Account("accountNumber1",1000,false);
		
		double toBalance=500;
		Account to= new Account("accountNumber1",1000,false);
		
		double transferAmount= 100;
		
		//act
		
		TransferStatus transferStatus= bankingCoreService.TransferMoneyToAnotherAccount(transferAmount, from, to);
		
		//assert
		
		assertThat(transferStatus,is(TransferStatus.Fraud));
		assertThat(from.getBalance(),is(fromBalance));
		assertThat(to.getBalance(),is(toBalance));
	}
	
	/*
	 * Scenario (Requirement): Transferring money from or to blocked accounts is not allowed.
	 */
	
	@Test
	public void TransferMoneyFromANormalAccountToABlockedAccount_IsNotAllowed() throws ConnectException {
		 
		//arrange
		
		double fromBalance=1000;
		Account from= new Account("accountNumber1",1000,false);
		
		double toBalance=500;
		Account to= new Account("accountNumber2",500,true);
		
		double transferAmount= 100;
		
		//act
		
		TransferStatus transferStatus= bankingCoreService.TransferMoneyToAnotherAccount(transferAmount, from, to);
		
		//assert
		
		assertThat(transferStatus,is(TransferStatus.AccountIsBlockedError));
		assertThat(from.getBalance(),is(fromBalance));
		assertThat(to.getBalance(),is(toBalance));
	}
	
	/*
	 * Scenario (Requirement): The amount parameter of Withdrawal operation should be greater than zero, otherwise 
	 * InvalidParameterException exception would be thrown
	 */
	
	@Test(expected = InvalidParameterException.class)
	public void WithdrawalWithNegativeAmount_IsNotAllowed() throws ConnectException {
		 
		//arrange
		
		double balance=1000;
		Account account= new Account("accountNumber1",balance,false);
		double amount= -100;
		
		//act
		
		TransferStatus transferStatus= bankingCoreService.Withdrawal(amount, account);
		
		//assert
		
		assertThat(account.getBalance(),is(balance));		
	}
	
	/*
	 * Scenario (Requirement): The amount parameter of Deposit operation should be greater than zero, otherwise 
	 * InvalidParameterException exception would be thrown
	 */
	
	@Test(expected = InvalidParameterException.class)
	public void DepositWithNegativeAmount_IsNotAllowed() throws ConnectException {
		 
		//arrange
		double balance=1000;
		Account account= new Account("accountNumber1",balance,false);
		double amount= -100;
		
		//act
		
		TransferStatus transferStatus= bankingCoreService.Deposit(amount, account);
		
		//assert
		assertThat(account.getBalance(),is(balance));
	}

	/*
	 * Scenario (Requirement): Deposits that result in an account balance more than the allowed maximum,
	 * should not proceed.
	 */
	
	@Test
	public void DepositThatExceedsTheMaximumAllowedBalance_IsNotAllowed() throws ConnectException {
		 
		//arrange
		double balance=1000;
		Account account= new Account("accountNumber1",balance,false,2000);
		double amount= 2000;
		
		//act
		
		TransferStatus transferStatus= bankingCoreService.Deposit(amount, account);
		
		//assert
		assertThat(transferStatus,is(TransferStatus.MaximumAllowedBalanceExceededError));
		assertThat(account.getBalance(),is(balance));

	}
	
	/*
	 * Scenario (Requirement): When transferring a money from one normal account to another normal account, 
	 * the final amount of the destination account should not exceed the maximum allowed.
	 */
	
	@Test
	public void MoneyTransferThatExceedsTheMaximumAllowedBalance_IsNotAllowed() throws ConnectException {
		 
		//arrange
		
		double fromBalance=1000;
		Account from= new Account("accountNumber1",fromBalance,false);
		
		double toBalance=1000;
		Account to= new Account("accountNumber2",toBalance,false,1000);
		double transferAmount= 2000;
		
		//act
		
		TransferStatus transferStatus= bankingCoreService.TransferMoneyToAnotherAccount(transferAmount, from, to);
		
		//assert
		assertThat(transferStatus,is(TransferStatus.MaximumAllowedBalanceExceededError));
		assertThat(from.getBalance(),is(toBalance));
		assertThat(from.getBalance(),is(fromBalance));
	}
	
	
	/*
	 * Scenario (Requirement): When transferring a money from one normal account to another normal account, 
	 * transfers that are defined as FRAUD are not allowed to be done. A transaction is considered to be fraud if
	 * the amount of transaction is more than 10K dollars. Both accounts should get blocked in such a scenario.
	 */
	@Test
	public void MoneyTransferThatResultsInFraud_IsNotAllowed() throws ConnectException {
		 
		//arrange
		

		//act
		
		
		//assert
			
		// don't forget to have assertions for all the asked consequences
	}

}
