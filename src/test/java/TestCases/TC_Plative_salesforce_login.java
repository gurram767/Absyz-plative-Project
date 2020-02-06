package TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Common.Screenshot;
import Pages.Plative_salesforce_login;
import TestBase.TestBase;
import TestData.GlobalTestData;

public class TC_Plative_salesforce_login extends TestBase{
	Plative_salesforce_login salesforcelogin;

	@BeforeClass
	public void beforeTest() throws Exception{
		init();		
		salesforcelogin = new Plative_salesforce_login(driver);
		log.info(getClass().getName()+" test started");
	}
	
	
		
		@Test
		public void loginsalesforce () throws Exception {
			Screenshot.infoScreenshot(driver, "loginpage");
			Plative_salesforce_login.salesforce_Login("operations@plative.com.partial","Pl@t!v3!");
	}
	@AfterClass(alwaysRun = true)
    public void endreport()
    {
		closeBrowser();
    }
}
