package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage {
	
	public ShoppingCartPage(WebDriver driver) {
		
		super(driver);
		
	}
	
	
	@FindBy(xpath="//a[@class='btn btn-primary']")
	WebElement btnCheckout;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	WebElement alrtWarning;
	
	
	
	public void clickCheckout() {
		btnCheckout.click();
	}
	
	public boolean isWarningAlertExist() {
		try {
			return(alrtWarning.isDisplayed());
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public String getWarningAlertText()	{
		return alrtWarning.getText();
	}
	

}
