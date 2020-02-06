package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.google.common.base.Function;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Common.ExcelUtil;
import Common.Screenshot;
import TestData.GlobalTestData;

public class TestBase {

	public static Properties Repository = new Properties();
	public File f;
	public FileInputStream FI;
	public static WebDriver driver;
	public String startTime;
	public static int indexSI = 1;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static Actions action;
	
	public static Logger log = Logger.getLogger(TestBase.class);
	private static final long DEFAULT_WAIT_SECONDS = 60;
	private static String lastAction;
	
	
	
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "/Reports/HtmlReports/Automaton Report"+".html", true);
		//extent = new ExtentReports(System.getProperty("user.dir") + "/Reports/HtmlReports/Automaton Report"+".html", false);

	}

	public void init() throws Exception {
		ExcelUtil.GetUserData();
		String log4jConfPath = "./src/main/resources/ConfigFiles/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		loadPropertiesFile();
		
		selectBrowser(GlobalTestData.CurrentBrowser);
		driver.get(Repository.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void loadPropertiesFile() throws IOException {
		f = new File(System.getProperty("user.dir") + "//src//main//resources//ConfigFiles//config.properties");
		FI = new FileInputStream(f);
		Repository.load(FI);


	}


	public static void selectBrowser(String browser){
	
			if(browser.equalsIgnoreCase("firefox")){
				//WebDriverManager.firefoxdriver().setup();
				//https://github.com/mozilla/geckodriver/releases
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/main/resources/Drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome")){
				//WebDriverManager.chromedriver().setup();
				//https://chromedriver.storage.googleapis.com/index.html
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/Drivers/chromedriver.exe");
				
				driver = new ChromeDriver();
			}
		}


	public void expliciteWait(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForPageToLoad(long timeOutInSeconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			System.out.println("Waiting for page to load...");
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println("Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
			Assert.assertFalse(true, "Timeout waiting for Page Load Request to complete.");

		}
	}

	public void clickWhenReady(By locator, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();

	}

	public WebElement fluentWait(final WebElement webElement, int timeinsec) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return webElement;
			}
		});

		return element;
	};

	public WebElement getWhenVisible(By locator, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;

	}

	public void waitFor(int sec) throws InterruptedException {
		Thread.sleep(sec * 1000);
	}


	public  void getScreenShot(String fileName) throws IOException {
		File outputFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(outputFile, new File(System.getProperty("user.dir") + "//Reports//screenshots//" + fileName + ".jpg"));
	}

/*	public static String now(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat fm = new SimpleDateFormat();
		return fm.format(cal.getTime());
	}

	public static void updateResult(int indexSI, String testCaseName, String testCaseStatus, String scriptName) throws IOException {
		String startDateTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());

		String userDirector = System.getProperty("user.dir");

		String resultFile = userDirector + "\\Reports\\HtmlReports\\TestHtmlReport.html";
		
		File file = new File(resultFile);
		System.out.println(file.exists());

		if (!file.exists()) {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<html>" + "\n");
			bw.write("<head><title>" + "Test execution report" + "</title>" + "\n");
			bw.write("</head>" + "\n");
			bw.write("<body>");
			bw.write("<font face='Tahoma'size='2'>" + "\n");
			bw.write("<u><h1 align='center'>" + "Test execution report" + "</h1></u>" + "\n");
			bw.flush();
			bw.close();
		}	
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(file, true));
		if (indexSI == 1) {
			bw1.write("<table align='center' border='0' width='70%' height='10'>");
			bw1.write("<tr><td width='70%' </td></tr>");
			bw1.write("<table align='center' border='1' width='70%' height='47'>");
			bw1.write("<tr>");
			bw1.write("<td colspan='2' align='center'><b><font color='#000000' face='Tahoma' size='2'>ScriptName :&nbsp;&nbsp;&nbsp;</font><font color='#0000FF'' face='Tahoma' size='2'>" + scriptName + " </font></b></td>");
			bw1.write("<td colspan='1' align='left'><b><font color='#000000' face='Tahoma' size='1'>Start Time :&nbsp;</font></b><font color='#0000FF'' face='Tahoma' size='1'>" + startDateTime + " </font></td>");
			bw1.write("</tr>");
			bw1.write("</tr>");
			bw1.write("<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>S.No</font></b></td>");
			bw1.write("<td  bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Test case ID : Test case Description </font></b></td>");

			bw1.write("<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>Result </font></b></td>");
			bw1.write("</tr>");
		}
		bw1.write("<tr>" + "\n");
		bw1.write("<td bgcolor='#FFFFDC'align='Center'><font color='#000000' face='Tahoma' size='2'>" + indexSI + "</font></td>" + "\n");
		bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>" + testCaseName + "</font></b></td>" + "\n");
		if (testCaseStatus == "Pass") {
			bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='center'><b><font color='Green' face='Tahoma' size='2'>" + testCaseStatus + "</font></b></td>" + "\n");
		} else {
			bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='center'><b><font color='red' face='Tahoma' size='2'>" + testCaseStatus + "</font></b></td>" + "\n");
		}
		bw1.write("</tr>" + "\n");
		bw1.write("</body>" + "\n");
		bw1.write("</html>");
		bw1.flush();
		bw1.close();

	}	
*/	
	public void getresult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			
			test.log(LogStatus.PASS, result.getName() + " test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
			String screen = Screenshot.capture(driver, getClass().getName());
			test.log(LogStatus.FAIL, test.addBase64ScreenShot("data:image/png;base64,"+screen));
			
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException {
		getresult(result);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(getClass().getSimpleName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		closeBrowser();
	}

	public void closeBrowser() {
		extent.endTest(test);
		extent.flush();
		driver.quit();
	}
	
	public String captureScreen(String fileName) {
		if (fileName == "") {
			fileName = "blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/automation/uiAutomation/screenshot/";
			destFile = new File((String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}
	public static WebDriver getdriver() {
		return driver;
		
	}
	
	/*//=========================================
				keywords
	===========================================*/
	
	//private static Map<String, WebDriver> drivers = new HashMap<>();

	
	
public static WebElement waitForClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(getdriver(), DEFAULT_WAIT_SECONDS);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(WebDriverException.class);
		
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
public static boolean click(By locator) {
	
		
		WebElement elm = waitForClickable(locator);
		if (elm == null) {
			return false;
		} else {
			elm.click();
			log.info("Click: " +locator.toString());
			return true;
		}
		
	}


public static WebElement waitForVisibile(By locator) {
	
	WebDriverWait wait = new WebDriverWait(getdriver(), DEFAULT_WAIT_SECONDS);
	wait.ignoring(ElementNotVisibleException.class);
	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	
}


public static boolean sendkeys(By locator, String data) {
	WebElement elm = waitForVisibile(locator);
	if(elm==null) {
		return false;
	}else {
		elm.sendKeys(data);
		log.info("Input Text: " +data+" - "+locator.toString());
		return true;
	}
	
}

public static void selectVisibleText(By locator, String visibleText) {
	WebElement elm = waitForVisibile(locator);
	Select se=new Select(elm);
	se.selectByVisibleText(visibleText);
	log.info("Select visible text in the dropdown : "+locator.toString());
}

public static void mouseHover(By locator) {
	WebElement elm = waitForVisibile(locator);
	//WebElement hoverElement = driver.findElement(elm);
	action = new Actions(driver);
	action.moveToElement(elm);
	action.click().build().perform();
	log.info("Hover the element : "+locator.toString());
	
}
public static String getElementText(By locator) {
	TestBase.lastAction = "Get Element text: " + locator.toString();
	log.info(TestBase.lastAction);
	WebElement elm = waitForClickable(locator);
	return elm.getText().trim();
}

//======
public static boolean scroll_Page(WebElement webelement, int scrollPoints)
{
    try
    {               
        Actions dragger = new Actions(driver);
        // drag downwards
        int numberOfPixelsToDragTheScrollbarDown = 10;
        for (int i = 10; i < scrollPoints; i = i + numberOfPixelsToDragTheScrollbarDown)
        {
            dragger.moveToElement(webelement).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release(webelement).build().perform();
        }
        Thread.sleep(3000);
        return true;
    }
    catch (Exception e)
    {
        e.printStackTrace();
        return false;
    }
}
}
