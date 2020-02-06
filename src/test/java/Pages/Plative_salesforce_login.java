package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestBase.TestBase;

public class Plative_salesforce_login extends TestBase {
	WebDriver driver;
	public static Logger log = Logger.getLogger(Plative_salesforce_login.class);

	public static By salesforce_email = By.id("username");
	public static By salesforce_password = By.id("password");
	public static By login = By.id("Login");
	// Verification
	public static By clickonProfile = By
			.xpath("(//*[@class= 'tooltipTrigger tooltip-trigger uiTooltip'])[5]/span[1]/div/span");
	public static By profilename = By.xpath("(//*[@class= 'profile-card-indent'])/h1/a");
	public static By emailverify = By.xpath(
			"(//*[@class= 'slds-form-element slds-form-element_readonly slds-grow slds-hint-parent override--slds-form-element'])[8]/div[2]/span/a");

	public Plative_salesforce_login(WebDriver driver){
		this.driver = driver;
	}

	public static void salesforce_Login(String email, String password) throws Exception {

		sendkeys(salesforce_email, email);

		sendkeys(salesforce_password, password);

		click(login);
		Thread.sleep(5000);

		// getDriver().switchTo().alert().accept();
		Thread.sleep(1000);

		click(clickonProfile);

		Thread.sleep(3000);
		click(profilename);
		Thread.sleep(5000);
		WebElement element = getdriver().findElement(emailverify);
		((JavascriptExecutor) getdriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(100);
		String actual = getdriver().findElement(emailverify).getText();
		System.out.println(actual);
		String Expected = "operations@plative.com";
		Assert.assertEquals(Expected, actual);

	}

}
