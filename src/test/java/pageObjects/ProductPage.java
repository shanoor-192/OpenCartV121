package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
	
	public ProductPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@name='quantity']")
	WebElement txtQuantity;
	
	@FindBy(xpath = "//button[@id='button-cart']")
	WebElement btnAddToCart;
	
	@FindBy(xpath = "//button//span[contains(text(),'item')]")
	WebElement btnShoppingCart;
	
	@FindBy(xpath = "//strong[normalize-space()='Checkout']")
	WebElement btnCheckout;
	
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement alrtSuccess;
	
	
	public void setQuantity(String quantity) {
		
		txtQuantity.clear();
		txtQuantity.sendKeys(quantity);
		
	}
	
	public boolean isQuantityExist() {
		try {
			return(txtQuantity.isDisplayed());
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public void clickAddCart() {
//		btnAddToCart.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", btnAddToCart);
	}
	
	public void clickShoppingCart() {
		btnShoppingCart.click();
	}
	
	public void clickCheckout() {
		btnCheckout.click();
	}
	
	public boolean isSuccessAlertExist() {
		try {
			return(alrtSuccess.isDisplayed());
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public String getSuccessAlertText() {
		return alrtSuccess.getText();
	}
	
}
