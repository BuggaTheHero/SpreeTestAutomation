package com.cucumber.MavenCucumberPrototype;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.testng.SpreeTestAutomation.HelperObjects.TestBase;
import com.testng.SpreeTestAutomation.PageObjects.LandingPage;
import com.testng.SpreeTestAutomation.Utilities.SeleniumDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddItemToCartStepDefinition extends TestBase {
	
 	LandingPage landingPage;;
	
    public AddItemToCartStepDefinition(SeleniumDriver seleniumDriver) 
    { 
    	super(seleniumDriver);
    }

	@BeforeMethod
	@Before("@AddItemToCart")
	public void setupTest() throws Exception
	{
		super.setupTest("NewRegistrationCustomerDoorToDoorShipping", "AddItemToCart");

	}
	
	@When("^I select a category$")
	public void selectACategory()  
	{ 
		try
		{
			landingPage = new LandingPage(super.seleniumDriver);
			landingPage.navigatetoLandingPage();
			landingPage.selectCategory();
			
			super.testResult.setStatus(true);
		}
		catch(Exception e)
		{
			super.testResult.setStatus(false);
			throw(e);
		}

	}

	@And("^I select a random product$")
	public void selectARandomProduct()
	{
		try
		{
			landingPage.selectARandomItem();
			super.testResult.setStatus(true);
		}
		catch(Exception e)
		{
			super.testResult.setStatus(false);
			throw(e);
		}
		
	}

	@And("^I select a size$")
	public void selectASize() 
	{
		try
		{
			landingPage.selectSize();
			super.testResult.setStatus(true);
		}
		catch(Exception e)
		{
			super.testResult.setStatus(false);
			throw(e);
		}
	}

	@And("^I click on the Add to Cart button$")
	public void clickOnTheAddToCartButton()
	{
		try
		{
			landingPage.clickOnAddToCartButton();
			super.testResult.setStatus(true);
		}
		catch(Exception e)
		{
			super.testResult.setStatus(false);
			throw(e);
		}

	}

	@Then("^I check that totals are populated correctly$")
	public void checkThatTotalsArePpulatedCorrectly() {
		try
		{
			landingPage.checkThatTotalsArePopulatedCorrectly();
			super.testResult.setStatus(true);
		}
		catch(Exception e)
		{
			super.testResult.setStatus(false);
			throw(e);
		}
	}
	
	@AfterMethod
	@After("@AddItemToCart")
	public void closeBrowser()
	{
		super.closeBrowser();
		htmlReport.SaveHTMLReport();
	
	}

}
