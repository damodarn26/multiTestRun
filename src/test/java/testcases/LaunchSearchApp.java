package testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.BaseClass;

public class LaunchSearchApp extends BaseClass {
	
	@Test
	
	public void googlePage() {
		logger=report.createTest("Google");
    	logger.info("Login Successful");
    	
    	driver.get("https://www.google.com");
		logger.pass("TEST PASSED");
		Reporter.log("Google Title :"+driver.getTitle(), true);

		Assert.assertEquals(12, 12);
	}

}
