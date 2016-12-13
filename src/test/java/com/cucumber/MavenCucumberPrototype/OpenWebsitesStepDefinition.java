package com.cucumber.MavenCucumberPrototype;



import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;

import com.testng.SpreeTestAutomation.Enummerables.Enums.LogType;
import com.testng.SpreeTestAutomation.HelperObjects.TestBase;
import com.testng.SpreeTestAutomation.PageObjects.LandingPage;
import com.testng.SpreeTestAutomation.Utilities.SeleniumDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class OpenWebsitesStepDefinition extends TestBase{

    public OpenWebsitesStepDefinition(SeleniumDriver seleniumDriver) 
    { 
    	super(seleniumDriver);
    }
    
	@BeforeMethod
	@Before("@OpenWebsite")
	public void setupTest() throws Exception
	{
		super.setupTest("SpreeTestCaseInputfile", "SpreeOpenWebsiteTest");
	}
	
	@Given("^I am on \"([^\"]*)\"$")
	public void navigateToWebsite(String url) throws Throwable {
		try{
			LandingPage landingPage = new LandingPage(super.seleniumDriver);
			landingPage.navigatetoLandingPage(url);
		}
		catch(Exception e){
			logger.printLog(LogType.ERROR, "navigateToWebsite() " + e.getMessage());
		}
		
	}

	@Then("^I check that the page title is visible$")
	public void checkWebTitle() throws Throwable {
		try{
			AssertJUnit.assertTrue(super.seleniumDriver.GetWebDriver().getTitle().toLowerCase().contains("online"));
			
		}
		catch(Exception e){
			logger.printLog(LogType.ERROR, "checkWebTitle() " + e.getMessage());
		}
	}
	
	@AfterMethod
	@After("@OpenWebsite")
	public void closeBrowser()
	{
		super.seleniumDriver.closeBrowser();
		htmlReport.SaveHTMLReport();;

	}

}
