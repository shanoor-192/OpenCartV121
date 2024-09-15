package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CategoryPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC006_E2ETest extends BaseClass {
	
	@Test(groups= {"master"})
	public void verify_e2e_purchase() {
		logger.info("**Starting TC006_E2ETest**");
		
		
		try {
			
			HomePage hp = new HomePage(driver);
			logger.info("Clicking on \'Phones & PDAs\' button...");
			hp.clickPhonesNPDAs();
			
			
			CategoryPage catpa = new CategoryPage(driver);
			boolean targetPage = catpa.isMyCategoryPageExist();
			
			logger.info("Verify the landing page...");
			if (targetPage==true) {
				logger.info("Counting number of results...");
				int countProd = catpa.countProductLi();
				logger.info("Count of products returned: "+countProd);
				
				logger.info("Scrolling into view of element...");
				scrollIntoViewElem(catpa.getElement(p.getProperty("searchProductName")));
				logger.info("Clicking element...");
//				catpa.clickHTCTouchHD();
				catpa.clickProduct(p.getProperty("searchProductName"));
				
				
				ProductPage prp = new ProductPage(driver);
				
				
				logger.info("Clearing and setting the value for quantity...");
				prp.setQuantity(p.getProperty("purchQuantity"));
				Thread.sleep(1000);
				logger.info("Adding to cart...");
				prp.clickAddCart();
				
				boolean targetAlrt = prp.isSuccessAlertExist();
				String successMsg = prp.getSuccessAlertText();
				
				logger.info("Verifying the cart has been updated...");
				if (targetAlrt==true) {
					logger.info("Message: " + successMsg);
					logger.info("Clicking on cart button...");
					prp.clickShoppingCart();
					logger.info("Proceeding to checkout...");
					prp.clickCheckout();
					
					ShoppingCartPage scp = new ShoppingCartPage(driver);
					
					
					logger.info("Clicking on checkout button...");
					scp.clickCheckout();
					logger.info("Verify if checkout is successful or not...");
					String warningMsg = scp.getWarningAlertText();
					boolean targetAlrt2 = scp.isWarningAlertExist();
					if (targetAlrt2==true) {
						logger.info("Message: " + warningMsg);
						Assert.assertTrue(true);
					}
					
					
				}
				
				else {
					Assert.assertTrue(false);
				}
				
			}
			
			else {
				Assert.assertTrue(false);
			}
		}
		
		catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("**Finished TC006_E2ETest**");
		
		
		
	}
	
	
	

}
