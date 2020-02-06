package TestCases;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Common.Screenshot;
import Pages.HomePage1;
import Pages.Leads;
import TestBase.TestBase;
import TestData.CreateIdeaTestData;

public class TC_002_CreateLead extends TestBase {
	
	HomePage1 homepage;
	Leads lead;
	CreateIdeaTestData objCreateIdeaTestData = new CreateIdeaTestData();
	
	@BeforeClass
	public void beforeTest() throws Exception{
		init();
		log.info(getClass().getName()+" test case started");
		homepage = new HomePage1(driver);
		lead = new Leads(driver);
		
	}
	
	@Test
	public void Sukh() throws Exception {
		
		objCreateIdeaTestData.GetData("TC_002_CreateLead");
		
		Leads.CreatLead(objCreateIdeaTestData);
		
		
		
	}
	
	@Test(dataProvider = "testData")
	public void CreateLead(String Username, String Password, String Runmode, String type, String firstname, String lastname, 
			String company, String desgination, String leadsource, String industry, String annualrevenue, String street, 
			String postalcode, String country, String description, String phone, String mobile, String email, String leadstatus) throws Exception {
		try {
			
			test.assignCategory("Regression");
		if(Runmode.equalsIgnoreCase("N")) {
			throw new SkipException("Runmode flag set as No");
		}
		
		//test=extent.startTest(getClass().getSimpleName(),"Login To the Application and create lead");
	
		Screenshot.infoScreenshot(driver, "loginpage");
		HomePage1.Login(Username, Password);
		Thread.sleep(4000);
		Leads.CreatLead(type, firstname, lastname, company, desgination, industry, leadsource, annualrevenue,
				street, postalcode, country, description, phone, mobile, email, leadstatus);
		HomePage1.Logout();
		}
		catch (Exception e) {
			
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
   
    }
      */
    @AfterClass
    public void endreport()
    {
    	closeBrowser();
        
        
    }
	
}
