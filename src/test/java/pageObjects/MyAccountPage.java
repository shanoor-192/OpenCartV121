package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	
	public MyAccountPage(WebDriver driver) {
		
		super(driver);
		
	}
	
	
	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogout;
	
	@FindBy(xpath = "//a[text()='View your order history']")
	WebElement lnkViewOrderHistory;
	
	
	public boolean isMyAccountPageExist() {
		try {
			return (msgHeading.isDisplayed());
		}
		catch (Exception e) {
			return false;
		}
		
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}
	
	
	public void clickViewOrderHistory() {
		lnkViewOrderHistory.click();
	}
	
}
