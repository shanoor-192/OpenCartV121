package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * Data is valid -- login succ. -- test pass -- logout
 *					login fail -- test fail
 * 
 * Data is invalid -- login succ. -- test fail -- logout
 * 					  login fail -- test pass
 * 
*/

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="Datadriven") // getting dataprov. from diff class
	public void verify_loginDDT(String email, String pass, String expRes) throws InterruptedException {

		logger.info("**Starting TC003_LoginDDT**");

		try {

		// homepage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();

		logger.info("Setting user details");
		// loginpage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pass);
		logger.info("Logging into account...");
		lp.clickLogin();

		// myaccountpage
		MyAccountPage myap = new MyAccountPage(driver);
		boolean targetPage = myap.isMyAccountPageExist();

		if (expRes.equalsIgnoreCase("Valid")) {
			if (targetPage == true) {
				logger.info("Logging out of account...");
				myap.clickLogout();
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}

		if (expRes.equalsIgnoreCase("Invalid")) {
			if (targetPage == true) {
				logger.info("Logging out of account..");
				myap.clickLogout();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}

		}

		catch (Exception e) {
			Assert.fail();
		}

		Thread.sleep(3000);
		logger.info("**Finished TC003_LoginDDT**");

	}

}
