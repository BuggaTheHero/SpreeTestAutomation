package com.testng.SpreeTestAutomation.TestNG;

import org.testng.annotations.Test;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.*;

import com.testng.SpreeTestAutomation.HelperObjects.TestBase;
import com.testng.SpreeTestAutomation.PageObjects.LandingPage;
import com.testng.SpreeTestAutomation.Utilities.SeleniumDriver;

public class AddItemToCartTest  extends TestBase 
{
 
 	LandingPage landingPage;
 			
	
    public AddItemToCartTest() 
    { 
    }
    
    public AddItemToCartTest(SeleniumDriver seleniumDriver) 
    { 
    	super(seleniumDriver);
    }
	 @Test
	@BeforeMethod
	 public void testExcecutionStart() 
	 {
		 super.setupTest("NewRegistrationCustomerDoorToDoorShipping", "AddItemToCart");
	 }
	 
	 @Test
	 public void addItemToCart()  throws  TimeoutException
	 {
			try
			{
				landingPage = new LandingPage(super.seleniumDriver);
				landingPage.addItemToCart();
			}
			catch(Exception e){
				throw(e);
			}
	 }
	 
	 @Test
	@AfterMethod
	 public void testExcecutionEnd() 
	 {
			super.closeBrowser();
	 }
 
}

