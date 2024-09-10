package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
		
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPass;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;
	
	
	public void setEmail(String eml) {
		txtEmail.sendKeys(eml);
	}
	
	public void setPassword(String pwd) {
		txtPass.sendKeys(pwd);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
}
