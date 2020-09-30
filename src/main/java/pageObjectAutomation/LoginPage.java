package pageObjectAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver ldriver) {
		this.driver=ldriver;
	}
	// 	Alternate normal way to locate elements is:
			//	By uname=By.name("email");
			// 	By pass=By.name("password");
			// 	By loginButton=By.xpath("xpath of element"):
			//	@FindBy(name="password") WebElement pass;

	@FindBy(name="email") WebElement uname;	

	@FindBy(how=How.NAME, using="password")	WebElement pass;
	
	@FindBy(xpath="//div[@class='ui fluid large blue submit button']") WebElement loginButton;
	
	public void loginToCRM (String usernameApp, String passwordApp) {
		System.out.println("I am in LoginPage.java: to login");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		uname.sendKeys(usernameApp);
		pass.sendKeys(passwordApp);
		loginButton.click();
	}
	

}
