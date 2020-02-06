package ObjectRepository;

import org.openqa.selenium.By;

public class HomePage_OR {
	//============Login=============
		public static By userName = By.name("user_name");
		public static By password = By.name("user_password");
		public static By btnLogin = By.id("submitButton");
		//============Logout=============
		
		public static By userProfile = By.xpath("//img[@src='themes/softed/images/user.PNG']");
		public static By lnkSigout = By.xpath("//a[contains(.,'Sign Out')]");
		

}
