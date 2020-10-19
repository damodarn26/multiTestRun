package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	public static WebDriver driverInstance;
	public static WebDriver startApplication(WebDriver driver, String browserName, String appURL) {

		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup(); 				// for latest 
			driver = new ChromeDriver();
			System.out.println("Browser is set as Chrome");
			break;
		case "firefox":
			System.out.println("Browser is set as Firefox");
			System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "ie":
			System.out.println("Browser is set as IE");
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
		driverInstance = driver;
		return driver;
	}

	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}
	
	public static void closeBrowser(WebDriver driver) {
		driver.close();
	}
	
	public static WebDriver getDriver() {
		return driverInstance;
	}

}
