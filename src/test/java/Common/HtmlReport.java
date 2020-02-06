/*package Common;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

import TestBase.TestBase;



public class HtmlReport {
	
	String FS = File.separator;
	String filePath= System.getProperty("user.dir")+FS+"Reports"+FS+"HtmlReport"+FS+"Testresults.html";
	
	
	public static void startReport(){
		
		 extent = new ExtentReports(filePath, true);
	        extent
	        .addSystemInfo("Host Name", "Buddha")
	        .addSystemInfo("Environment", "Test")
	        .addSystemInfo("User Name", "Rajesh Buddha");
	        
	        extent.loadConfig(new File("./src/main/resources/ConfigFiles/extent-config.xml"));
	        //extent.startTest(testName);
		 	 }

	
	public static void endReport(){
		extent.flush();
		extent.close();
	}


	
	
}
*/