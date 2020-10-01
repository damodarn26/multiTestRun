package testcases;
 
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pageObjectAutomation.LoginPage;
import pages.BaseClass;	
 
public class LoginTestCRM extends BaseClass  {
	

    @Test
    public void loginApp() {
    	// This logger is responsible for all logging info in this class.
		// logger is for Extent Report
    	logger=report.createTest("Login to CRM application");
    	
    	// This will initialize all element locator of the class and return the class as an Object
//    	LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);
//    	logger.info("Starting the CRM via Login Page");    	
//    	loginPage.loginToCRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
    	
    	driver.get("https://www.linkedin.com");
    	logger.pass("Login Successful");
    	System.out.println("LoginTestCRM-loginApp: Successful");
    }
    

    @Test
    public void logoutApp() {
    	logger=report.createTest("Logout to CRM application");

		driver.get("https://www.facebook.com/help");
    	logger.fail("Logout FAILED");
    	System.out.println("LogoutTestCRM-logoutApp: Failed");
    	
    }
}