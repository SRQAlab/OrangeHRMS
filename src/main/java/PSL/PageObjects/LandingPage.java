package PSL.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PSL.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		// initialization of driver
		this.driver = driver;
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	// Pagefactory design pattern as below.

	@FindBy(xpath="//input[@name='username']")
	WebElement userEmail;

	@FindBy(xpath = "//input[@name='password']")
	WebElement passwordEle;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement submit;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
	WebElement errorMessage;

//--------------------------------------------------------------------------------------------------------------------	
	public void loginApplication(String email, String password) // Action methods to be created like this.
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
	}

//--------------------------------------------------------------------------------------------------------------------	

	public void goTo() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
