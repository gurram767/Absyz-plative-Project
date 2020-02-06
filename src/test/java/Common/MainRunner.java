package Common;

import TestData.CreateIdeaTestData;
import TestData.GlobalTestData;

public class MainRunner {

	@org.testng.annotations.Test
	public static void Test() throws Exception {
		// TODO Auto-generated method stub
		
		ExcelUtil.GetUserData();
		CreateIdeaTestData objCreateIdeaTestData = new CreateIdeaTestData();
		objCreateIdeaTestData.GetData("TC_001_CreateIdea");
		
		Thread.sleep(5000);
		CreateIdeaTestData objCreateIdeaTestData2 = new CreateIdeaTestData();
		objCreateIdeaTestData2.GetData("Test11");
		
		System.out.println(objCreateIdeaTestData.toString());
		System.out.println(objCreateIdeaTestData2.toString());
		
		System.out.println(GlobalTestData.Requester1.getDescription());
		System.out.println(GlobalTestData.CurrentBrowser);

	}

}
