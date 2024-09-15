package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CategoryPage extends BasePage {

	
	public CategoryPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath = "//h2[normalize-space()='Phones & PDAs']")
	WebElement msgCategoryHeading;
	
	@FindBy(xpath = "//div[@class='row'][2]//img")
	List <WebElement> productLi;
	
	@FindBy(xpath = "//img[@title='iPhone']")
	WebElement imgiPhone;
	
	@FindBy(xpath = "//img[@title='Palm Treo Pro']")
	WebElement imgPalmTreoPro;
	
	@FindBy(xpath = "//img[@title='HTC Touch HD']")
	WebElement imgHTCTouchHD;
	
	
	
	public boolean isMyCategoryPageExist() {
		try { 
			return (msgCategoryHeading.isDisplayed());
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public int countProductLi() {
		try {
			return (productLi.size());
		}
		catch (Exception e) {
			return 0;
		}
	}
	
	public void clickHTCTouchHD() {
		imgHTCTouchHD.click();
	}
	
	public WebElement getElement(String element) {
		
			switch (element) {
			case "HTC Touch HD" : return imgHTCTouchHD;
			case "Palm Treo Pro" : return imgPalmTreoPro;
			case "iPhone" : return imgiPhone;
			default: return null; }
		
	}
	
	// dynamic 
	public void clickProduct(String pName) {
		try {
			for (int i = 0; i < productLi.size(); i++) {
				if (((productLi.get(i)).getAttribute("title")).equalsIgnoreCase(pName)) {
					productLi.get(i).click();
				}
			}
		}
		catch (Exception e) {
			Assert.fail();
		}
	}
	
	
}
