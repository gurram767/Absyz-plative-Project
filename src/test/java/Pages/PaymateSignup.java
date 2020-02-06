package Pages;

import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import TestBase.TestBase;

public class PaymateSignup  extends TestBase{
	static WebDriver driver;
	private static By registernow  = By.xpath("//*[@id=\"UpnlHome\"]/div[6]/div/div[1]/a");
	private static By firstname  = By.id("txtFirstName");
	private static By lastname  = By.id("txtLastName");
	private static By email  = By.id("txtEmailaddress");
	private static By phone  = By.id("txtMobileno");
	//private static By terms  = By.xpath("//*[@id='cphContents_pnlContents']/div[7]/div/label/span[5]");
	private static By terms  = By.cssSelector("#cphContents_pnlContents > div:nth-child(7) > div > label > span:nth-child(8)");
	//#cphContents_pnlContents > div:nth-child(7) > div > label > span:nth-child(8)
	//	private static By terms1  = By.xpath("(//span[@class='mdl-checkbox__ripple-container mdl-js-ripple-effect mdl-ripple--center'])[2]/preceding::span[1]");
	private static By accept  = By.xpath("//*[@id=\"tnc-container\"]/div[2]/a/span");

	private static By singupbutton  = By.id("cphContents_btnSubmit");
	public PaymateSignup (WebDriver driver) {
		this.driver = driver;
	}

	public static void signupProcess() throws Exception {
		click(registernow);
		sendkeys(firstname, "suryateja");
		sendkeys(lastname, "gurram");
		sendkeys(email, "gurram.suryateja@gmail.com");
		sendkeys(phone, "9553950149");
		Thread.sleep(3000);
		click(terms);

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);}
		WebElement scrollArea = driver.findElement(By.cssSelector("#tnc-container > div.row-fluid > a > span"));
		scroll_Page(scrollArea ,100);		
		System.out.println("Clicked on Acccept");
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		System.out.println("Switch to default");
		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(1000);
		click(singupbutton);
		Thread.sleep(3000);
	}
}
