package TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Common.Screenshot;
import Pages.HomePage1;
import TestBase.TestBase;
import TestData.GlobalTestData;

public class TC_001_LogIn extends TestBase {
	
	HomePage1 homepage;
	
	

	@BeforeClass
	public void beforeTest() throws Exception{
		init();		
		homepage = new HomePage1(driver);
		log.info(getClass().getName()+" test started");
	}
	
	@Test()
	public void Login() throws Exception {
		
		try {
			
			test.assignCategory("Regression");
		
		//test=extent.startTest(getClass().getSimpleName(),"Login To the Application and create lead");
		
		
		Screenshot.infoScreenshot(driver, "loginpage");
		HomePage1.Login(GlobalTestData.Requester2.getLoginID(), GlobalTestData.Requester2.getPassword());
		Thread.sleep(4000);
		HomePage1.Logout();
		}catch(Exception e) {
		
		}
		extent.endTest(test);
		extent.flush();
	}
	
	
	/*@AfterMethod
    public void getResult(ITestResult result) throws IOException
    {
		if(result.getStatus()==ITestResult.FAILURE)
        {
			test.log(LogStatus.FAIL, result.getThrowable());
            String str = Screenshot.capture(driver, getClass().getSimpleName());
            test.log(LogStatus.FAIL, test.addBase64ScreenShot("data:image/png;base64,"+str));
         
		}
		extent.endTest(test);
		extent.flush();
       
    }*/
      
	@AfterClass(alwaysRun = true)
    public void endreport()
    {
		closeBrowser();
    }
	
}
