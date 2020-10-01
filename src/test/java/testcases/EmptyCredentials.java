package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BaseClass;

public class EmptyCredentials extends BaseClass {
	
	@Test
	
	public void noSubmitDetails() {
		
		driver.get("https://www.google.com");
		System.out.println("In second class");
		logger=report.createTest("Submitting without credentials");
		Assert.assertEquals(12, 12);
	}

}
