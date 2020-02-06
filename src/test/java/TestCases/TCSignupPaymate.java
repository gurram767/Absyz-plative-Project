package TestCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.PaymateSignup;
import TestBase.TestBase;

public class TCSignupPaymate extends TestBase {
	PaymateSignup   pay;
	@BeforeClass
	public void beforeTest() throws Exception{
		init();		
		pay = new PaymateSignup(driver);
		log.info(getClass().getName()+" test started");
	
	}
	@Test
	public void TCsignuppay () throws Exception {
		PaymateSignup.signupProcess();
		
	}
}
