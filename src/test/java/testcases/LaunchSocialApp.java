package testcases;
 
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.BaseClass;	
 
@Listeners(utility.CustomListeners.class)
public class LaunchSocialApp extends BaseClass  {
	
    @Test
    public void LaunchFacebook() {
    	//logger=report.createTest("Facebook");
    	//logger.info("Launching Facebook Page");    	
		
    	driver.get("https://www.facebook.com/help");
		//logger.fail("Logout FAILED");
		Reporter.log("Facebook Title :"+driver.getTitle(), true);
    }
    
    @Test
    public void LaunchLinkedIn() {
    	//logger=report.createTest("LinkedIn");
    	//logger.info("Launching LinkedIn Page");       	
    	// This will initialize all element locator of the class and return the class as an Object
//    	LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);
//    	loginPage.loginToCRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
    	
    	driver.get("https://www.linkedin.com");
    	//logger.pass("Login SUCCESS");
		Reporter.log("LinkedIn Title :"+driver.getTitle(), true);
    }
}