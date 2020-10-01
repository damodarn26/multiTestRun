package pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
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
		Reporter.log("TestNG: Setting up reports and test is getting ready", true);
		excel = new ExcelDataProvider();
		ExtentHtmlReporter extent = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "/Reports/FreeCRM_" + Helper.getCurrentDateTime() + ".html"));
		report = new ExtentReports();
		report.attachReporter(extent);
	}
	
	@BeforeTest
	public void setTest() {
		config = new ConfigurationDataProvider();
	}
	
	@Parameters("browser")
	@BeforeClass 
	public void configTest(String browser) {
		System.out.println();
		System.out.println("I am in BeforeClass:setup");
		System.out.println("driver :  " + driver );
		System.out.println("browser : "+ browser);
		System.out.println("testURL " + config.getFromConfig("testUrl"));
		driver = BrowserFactory.startApplication(driver, browser, config.getFromConfig("testUrl"));
//		WebDriverManager.chromedriver().setup(); 				// for latest 
//		driver = new ChromeDriver();
		System.out.println("driver :" + driver);
	}

	@AfterClass
	public void setAfterTest() {
		System.out.println("driver :" + driver);
		BrowserFactory.quitBrowser(driver);
		System.out.println("I am in AfterClass: Quit");
	}
	
	@AfterMethod
	public void setAfterMethod(ITestResult result) throws IOException {
		System.out.println("I am in AfterMethod: ScreenshotCapture Report");
//		Helper.captureScreenshot(driver);
//		if (result.getStatus() == ITestResult.FAILURE) {
//			logger.fail("Test FAILED",
//					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
//		} else if (result.getStatus() == ITestResult.SUCCESS) {
//			logger.pass("Test PASSED",
//					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
//		} else if (result.getStatus() == ITestResult.SKIP) {
//			logger.skip("Test SKIPPED",
//					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
//		}
//		report.flush();
	}
}