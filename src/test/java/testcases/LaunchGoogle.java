package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BaseClass;

public class LaunchGoogle extends BaseClass {
	
	@Test
	
	public void googlePage() {
	
		driver.get("https://www.google.com");
		System.out.println("LaunchGoogle - "+driver.getTitle());
//		logger=report.createTest("Submitting without credentials");
//		Assert.assertEquals(12, 12);
	}

}
