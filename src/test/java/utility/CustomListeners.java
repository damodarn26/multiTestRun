package utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class CustomListeners implements ITestListener {
	
	static String fileName = System.getProperty("user.dir") + "/Reports/FreeCRM_" + Helper.getCurrentDateTime() + ".html";

	private static ExtentReports extent = ExtentManager.createInstance(fileName);
	private static ExtentTest test;
	private static ThreadLocal<ExtentTest> report = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
		report.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		report.get().pass("Test Case : " + result.getName().toUpperCase() + " PASSED");
	}

	public void onTestFailure(ITestResult result) {
		report.get().fail("Test Case : " + result.getName().toUpperCase() + " FAILED");
	}
	
	public void onTestSkipped(ITestResult result) {
		report.get().skip("Test Case : " + result.getName().toUpperCase() + " skipped");
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
