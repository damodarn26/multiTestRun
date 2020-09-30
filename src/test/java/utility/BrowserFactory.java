package utility;

import java.net.URL;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

//	If running script locally via pom or testng or as a maven build
// 	WebDriverManager : https://github.com/bonigarcia/webdrivermanager
//	This is when you don't want to have driver.exe and manager webdriver automatically
		
	public static WebDriver startApplication(WebDriver driver, String browserName, String appURL) {
		switch (browserName.toLowerCase()) {
		case "chrome":
//			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
//			WebDriverManager.chromedriver().version("2.36")setup(); 	// for specific version
//			WebDriverManager.chromedriver().setup(); 				// for latest 
//			driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.setExperimentalOption("useAutomationExtension", "false");
			WebDriverManager.chromedriver().setup(); 				// for latest 
			driver = new ChromeDriver(options);
			System.out.println("Browser is set as Chrome");
			break;
		case "firefox":
			System.out.println("Browser is set as Firefox");
			System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "ie":
			System.out.println("Browser is set as IE");
			// System.setProperty("webdriver.ie.driver", "./Drivers/IEDriverServer.exe");
			// driver = new InternetExplorerDriver();
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Looking forward to the Weekend");
		}
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}

}
