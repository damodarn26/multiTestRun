package pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utility.BrowserFactory;
import utility.ConfigurationDataProvider;
import utility.ExcelDataProvider;
import utility.Helper;

public class BaseClass {

	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigurationDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;

	@BeforeSuite
	public void setUpSuite() {
		Reporter.log("IN BEFORE SUITE", true);
		excel = new ExcelDataProvider();
	}
	
	@BeforeTest
	public void setBeforeTest() {
			Reporter.log("IN BEFORE TEST", true);
	}
	
	@Parameters("browser")
	@BeforeClass 
	public void configTest(String browser) {
		Reporter.log("IN BEFORE CLASS", true);
		config = new ConfigurationDataProvider();

		System.out.println();
		Reporter.log("IN BEFORE CLASS :", true);
		driver = BrowserFactory.startApplication(driver, browser, config.getFromConfig("testUrl"));
		System.out.println("driver :" + driver);
	}


	@BeforeMethod
	public void setBeforeMethod(){
		Reporter.log("IN BEFORE METHOD :", true);
		ExtentHtmlReporter extent = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "/Reports/FreeCRM_" + Helper.getCurrentDateTime() + ".html"));
		report = new ExtentReports();
		report.attachReporter(extent);
	}
	
	@AfterMethod
	public void setAfterMethod(ITestResult result) throws IOException {
		Reporter.log("IN AFTER METHOD :", true);
		System.out.println("I am in AfterMethod: ScreenshotCapture Report");
//		Helper.captureScreenshot(driver);
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.fail("Test FAILED",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.pass("Test PASSED",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.skip("Test SKIPPED",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		report.flush();
	}
	
	@AfterClass
	public void setAfterTest() {
		Reporter.log("IN AFTER CLASS :", true);
		System.out.println("driver :" + driver);
		BrowserFactory.quitBrowser(driver);
	}
	
	@AfterSuite
	public void setAfterSuite() {
		Reporter.log("IN AFTER SUITE", true);
	}
}