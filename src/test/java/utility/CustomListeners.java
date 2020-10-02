package utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class CustomListeners implements ITestListener {
	
	static String fileName = System.getProperty("user.dir") + "/Reports/FreeCRM_" + Helper.getCurrentDateTime() + ".html";

	private static ExtentReports extent = ExtentManager.createInstance(fileName);
	public static ExtentTest test;

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		test.pass("Test Case : " + result.getName().toUpperCase() + " PASSED");
	}

	public void onTestFailure(ITestResult result) {
		test.fail("Test Case : " + result.getName().toUpperCase() + " FAILED");
	}
	
	public void onTestSkipped(ITestResult result) {
		test.skip("Test Case : " + result.getName().toUpperCase() + " skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO
	}

	public void onStart(ITestContext context) {
		// TODO 
	}

	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}

}
