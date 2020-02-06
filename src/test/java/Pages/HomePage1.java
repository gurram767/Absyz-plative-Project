package Pages;

import static ObjectRepository.HomePage_OR.btnLogin;
import static ObjectRepository.HomePage_OR.lnkSigout;
import static ObjectRepository.HomePage_OR.password;
import static ObjectRepository.HomePage_OR.userName;
import static ObjectRepository.HomePage_OR.userProfile;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import TestBase.TestBase;



public class HomePage1 extends TestBase{
	
	WebDriver driver;
		
	public static Logger log = Logger.getLogger(HomePage1.class);
	
	
	
	public HomePage1(WebDriver driver){
		this.driver = driver;
	}
	
	public static void Login(String Username, String Password) throws Exception {
		
		sendkeys(userName, Username);
		sendkeys(password, Password);
		click(btnLogin);
		try {
			mouseHover(userProfile);
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			mouseHover(userProfile);
		}
		
		String actualText = getElementText(lnkSigout);
		String expectedText = "Sign Out";
		System.out.println(actualText);
		Assert.assertTrue(actualText.contains(expectedText));
		System.out.println("Text verifyed");
		//assertTrue("Your error message", actualText.contains(expectedText));
			
	}
	
	public static void Logout() {
		mouseHover(userProfile);
		click(lnkSigout);
		
	}

}
