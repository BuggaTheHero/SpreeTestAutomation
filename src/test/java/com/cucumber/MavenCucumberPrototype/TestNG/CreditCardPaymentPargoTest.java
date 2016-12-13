package com.cucumber.MavenCucumberPrototype.TestNG;

import org.testng.annotations.Test;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.*;

import com.testng.SpreeTestAutomation.HelperObjects.TestBase;
import com.testng.SpreeTestAutomation.PageObjects.LandingPage;
import com.testng.SpreeTestAutomation.PageObjects.LoginRegisterPage;
import com.testng.SpreeTestAutomation.PageObjects.ShippingPage;
import com.testng.SpreeTestAutomation.Utilities.SeleniumDriver;

public class CreditCardPaymentPargoTest  extends TestBase 
{
 
 	LandingPage landingPage;
 	LoginRegisterPage loginRegisterPage;
 	ShippingPage shippingPage;
 			
	
    public CreditCardPaymentPargoTest() 
    { 
    }
    
    public CreditCardPaymentPargoTest(SeleniumDriver seleniumDriver) 
    { 
    	super(seleniumDriver);
    }
    
	 @Test
	@BeforeMethod
	 public void testExcecutionStart() 
	 {
		 super.setupTest("NewRegistrationCustomerPargoShipping", "CreditCardPaymentPargo");
	 }
	 
	 @Test
	 public void addItemToCart()  throws  TimeoutException
	 {
			try
			{
				landingPage = new LandingPage(super.seleniumDriver);
				loginRegisterPage = new LoginRegisterPage(super.seleniumDriver);
				shippingPage = new ShippingPage(super.seleniumDriver);
				
				landingPage.addItemToCart();
				loginRegisterPage.register(testData);
				shippingPage.enterShippingAddress(testData);
				shippingPage.selectShippingMethod(testData);

				
			}
			catch(Exception e)
			{
				throw(e);
			}
	 }
	 
	 @Test
	@AfterMethod
	 public void testExcecutionEnd() {
			super.closeBrowser();
	 }
 
}