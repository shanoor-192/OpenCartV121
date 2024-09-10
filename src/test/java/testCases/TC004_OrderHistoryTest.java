package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.OrderHistoryPage;
import testBase.BaseClass;

public class TC004_OrderHistoryTest extends BaseClass {

	
	@Test(groups= {"sanity","master"})
	public void verify_order_history() {
		logger.info("**Starting TC004_OrderHistoryTest**");
		
		try {
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			
			MyAccountPage myacc = new MyAccountPage(driver);
			boolean targetPage1 = myacc.isMyAccountPageExist();
			
			
			if (targetPage1==true) {
				logger.info("Clicking on \'View Order History\' button...");
				myacc.clickViewOrderHistory();
				
				OrderHistoryPage ohp = new OrderHistoryPage(driver);
				boolean targetPage2 = ohp.isMyOrderHistoryPageExist();
				int res = ohp.countOrders();
				String resShow = ohp.messageShowingResult();
				
				
				if (targetPage2==true) {
					
					logger.info("Verifying order contents matches...");
					try {
						System.out.println(ohp.countOrders());
						System.out.println(ohp.messageShowingResult());
						logger.info("Expecting " + res + " to be inside of " + resShow);
						Assert.assertTrue(resShow.contains(String.valueOf(res)));
						logger.info("Logging out of account...");
						myacc.clickLogout();
					}
					catch (Exception e) {
						Assert.fail();
					}
					
				}
				else {
					Assert.fail();
				}
				
				
				
			}
			
			
			
		} catch (Exception e) {
			Assert.fail();
		}
		
		
		
	}
	
	
	
	
	
	
	
	
}
