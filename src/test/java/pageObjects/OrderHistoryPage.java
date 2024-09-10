package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;


public class OrderHistoryPage extends BasePage {

	WebDriver driver;
	
	public OrderHistoryPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath = "//h1[text()='Order History']")
	WebElement msgHeading;
	
	@FindBy(xpath = "//div[contains(text(),'Showing')]")
	WebElement msgResult;
	
	@FindBy(xpath = "//table[@class='table table-bordered table-hover']//tbody//tr")
	List<WebElement> orderLi;
	
	
	public boolean isMyOrderHistoryPageExist() {
		
		try {
			return (msgHeading.isDisplayed());
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public String messageShowingResult() {
		
		return (msgResult.getText());
		
	}
	
	public int countOrders() {
		
		try {
			return (orderLi.size());
		}
		catch (Exception e) {
			return 0;
		}
		
		
	}
	
	
	
	
	
}
