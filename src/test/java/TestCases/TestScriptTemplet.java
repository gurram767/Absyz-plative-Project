package TestCases;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import TestBase.TestBase;

public class TestScriptTemplet extends TestBase{
	
	
	@BeforeClass
	public void beforeTest() throws Exception{
		init();
		log.info(getClass().getName()+" test case started");
		
	}
	
	@Test
	public void CreateLead() throws Exception {
		try {
			test.assignCategory("Regression");
		
			
		
		}
		catch (Exception e) {
			
		}
		extent.endTest(test);
		extent.flush();
	}
	

    @AfterClass
    public void endreport()
    {
    	closeBrowser();
        
        
    }
	
}
