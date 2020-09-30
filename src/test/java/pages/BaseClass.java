package pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
		// true : will print in System.out
		// Reporter.log is for TestNG reports
		// System.out is for Console output
		// logger is for Extent Report
		
		//BeforeSuite 	: 	before all test in the suite
		//BeforeTest	:	before any test method E-- clasees inside <test>
		//BeforeClass	: 	before first test method E-- current class is invoked
		//BeforeMethod	:	before each test method
		
    	excel= new ExcelDataProvider();
    	config=new ConfigurationDataProvider();
    	ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/FreeCRM_"+Helper.getCurrentDateTime()+".html"));
    	report=new ExtentReports();
    	report.attachReporter(extent);
	}
	
//	@BeforeTest
//	public void testConfig() {
//    	excel= new ExcelDataProvider();
//    	config=new ConfigurationDataProvider();
//    	ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/FreeCRM_"+Helper.getCurrentDateTime()+".html"));
//    	report=new ExtentReports();
//    	report.attachReporter(extent);
//	}
	
	@Parameters("browser")   // use testng.xml and pass this xmlFile value as Mvn cmd argument
	@BeforeTest			// Run via testng.xml file where param are passed from xml
	public void setup(String browser){
	System.out.println("I am in BeforeClass:setup");
	driver=BrowserFactory.startApplication(driver, browser, config.getFromConfig("testUrl")); 
	System.out.println("driver :" +driver);
	}
	
	@AfterTest
	public void tearDown(){
		config.setIntoConfig("test", "qa");  // This is an example to add new value in config.properties file
    	BrowserFactory.quitBrowser(driver);		
    	System.out.println("I am in AfterClass: Quit");
	}
	
	@AfterMethod
	public void StatusCapture(ITestResult result) throws IOException {
		
		//((JavascriptExecutor) driver).executeScript("sauce:job-result= " + (result.isSuccess() ? "passed" : "failed"));
		
		if(result.getStatus()==ITestResult.FAILURE) {
			logger.fail("Test FAILED", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			logger.pass("Test PASSED", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			logger.skip("Test SKIPPED", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}		
		//logger.log
		//logger.info
		//logger.addScreenCaptureFromPath
		report.flush();
	}

}
