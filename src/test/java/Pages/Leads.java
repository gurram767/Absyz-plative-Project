package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import TestBase.TestBase;
import TestData.CreateIdeaTestData;


public class Leads extends TestBase {
	
	WebDriver driver;
	
	private static By btnLeads = By.linkText("Leads");
	private static By btnCreateLead = By.xpath("//img[@alt='Create Lead...']");
	private static By ddFirstNameType = By.name("salutationtype");
	private static By txtFirstName = By.name("firstname");
	private static By txtLastName = By.name("lastname");
	private static By txtCompany = By.name("company");
	private static By txtTitle = By.id("designation");
	private static By ddLeadsource = By.name("leadsource");
	private static By ddIndustry = By.name("industry");
	private static By txtannualrevenue = By.name("annualrevenue");
	private static By txtNoofEmployees = By.id("noofemployees");
	private static By txtSecondaryeMail = By.id("secondaryemail");
	private static By txtPhone = By.id("phone");
	private static By txtMobile = By.id("mobile");
	private static By txtFax = By.id("fax");
	private static By txtemail = By.id("email");
	private static By txtwebsite = By.name("website");
	private static By ddleadstatus = By.name("leadstatus");
	private static By ddrating = By.name("rating");
	private static By btnassigntype = By.xpath("//input[@value='U']");
	private static By ddassigned_user_id = By.name("assigned_user_id");
	private static By txtStreet = By.xpath("//textarea[contains(@name,'lane')]");
	private static By txtPastalcode = By.name("code");
	private static By txtcountry = By.name("country");
	private static By txtcity = By.name("city");
	private static By txtstate = By.name("state");
	private static By txtdescription = By.className("detailedViewTextBoxOn");
	private static By btnSave = By.xpath("//input[@class='crmbutton small save']");
	
	
	
	
	public Leads(WebDriver driver) {
		this.driver = driver;
	}


	public static void CreatLead(CreateIdeaTestData objTd) {
		
		selectVisibleText(ddFirstNameType, objTd.getBenfit());
		
		
	}

	public static void CreatLead(String type, String firstname, String lastname, String company, String desgination, String industry, String leadsource, String annualrevenue, String street, String postalcode, String country, String description, String phone, String mobile, String email, String leadstatus) throws InterruptedException {
		click(btnLeads);
		click(btnCreateLead);
		selectVisibleText(ddFirstNameType, type);
		sendkeys(txtFirstName, firstname);
		sendkeys(txtLastName, lastname);
		sendkeys(txtCompany, company);
		sendkeys(txtTitle, desgination);
		selectVisibleText(ddLeadsource, leadsource);
		selectVisibleText(ddIndustry, industry);
		
		sendkeys(txtannualrevenue, annualrevenue);
		sendkeys(txtStreet, street);
		sendkeys(txtPastalcode, postalcode);
		sendkeys(txtcountry, country);
		sendkeys(txtdescription, description);
		sendkeys(txtPhone, phone);
		sendkeys(txtMobile, mobile);
		sendkeys(txtemail, email);
		selectVisibleText(ddleadstatus, leadstatus);
		//KeywordUtil.click(btnSave);
		
		
	}
}
