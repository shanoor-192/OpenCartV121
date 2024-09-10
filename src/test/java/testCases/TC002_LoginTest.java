package testCases;

import static org.testng.Assert.assertFalse;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups= {"Sanity","Master"})
	public void verify_login(){
		
		logger.info("**Starting TC002_LoginTest**");
		
		try {
		// homepage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		logger.info("Setting user details");
		// loginpage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		
		// myaccountpage
		MyAccountPage myap = new MyAccountPage(driver);
		boolean targetPage = myap.isMyAccountPageExist();
		
		
		// validate page
		Assert.assertTrue(targetPage);
		
		}
		
		catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("**Finished TC002_LoginTest**");
		
		
		
		
		
	}
	
	
	
}
