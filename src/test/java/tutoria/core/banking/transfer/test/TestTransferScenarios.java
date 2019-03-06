package tutoria.core.banking.transfer.test;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import java.net.ConnectException;
import java.security.InvalidParameterException;

import tutorial.core.banking.services.CoreService;
import tutorial.core.banking.services.InterBankingService;

/*
 * Task: 
 * 
 * There is a problem in CoreService.isThisAFraudTransfer() method
 * 
 * we are sure that at commit 9ee2ea9d0af4eba2bc6ee96f82bd4fb5df0ff895, it was ok.
 * 
 *  your job is to find the bug inducing commit using git_bisect.
 *  
 *  you can automate git-bisect using "git bisect run ./gradlew test --tests  *.{testclassname}.{testmethodname}"
 */


@RunWith(MockitoJUnitRunner.class)
public class TestTransferScenarios {
	
	
	
}
