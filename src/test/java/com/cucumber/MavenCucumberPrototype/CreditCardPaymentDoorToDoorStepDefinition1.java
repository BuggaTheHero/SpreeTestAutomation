package com.cucumber.MavenCucumberPrototype;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.testng.SpreeTestAutomation.HelperObjects.TestBase;
import com.testng.SpreeTestAutomation.PageObjects.LandingPage;
import com.testng.SpreeTestAutomation.PageObjects.LoginRegisterPage;
import com.testng.SpreeTestAutomation.PageObjects.ShippingPage;
import com.testng.SpreeTestAutomation.Utilities.SeleniumDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreditCardPaymentDoorToDoorStepDefinition1 extends TestBase {
	
	private LoginRegisterPage loginRegisterPage;
	private ShippingPage shippingPage;
	
    public CreditCardPaymentDoorToDoorStepDefinition1(SeleniumDriver seleniumDriver) 
    { 
    	super(seleniumDriver);
    }

	@BeforeMethod
	@Before("@CreditCardPaymentDoorToDoor")
	public void setupTest() throws Exception
	{
		super.setupTest("NewRegistrationCustomerDoorToDoorShipping", "CreditCardPaymentDoorToDoor");

	}
	
	@When("^I add item to cart$")
	public void addItemToCart() throws Throwable {
		
		AddItemToCartStepDefinition  addItemToCart = new AddItemToCartStepDefinition(super.seleniumDriver);
	    addItemToCart.selectACategory();
	    addItemToCart.selectARandomProduct();
	    addItemToCart.selectASize();
		addItemToCart.clickOnTheAddToCartButton();
	}

	@When("^I register$")
	public void register() throws Throwable {
		loginRegisterPage = new LoginRegisterPage(super.seleniumDriver);
		loginRegisterPage.register(super.testData);
	}

	@When("^I enter shipping address$")
	public void enterShippingAddress() throws Throwable {
		shippingPage = new ShippingPage(super.seleniumDriver);
		
		
		shippingPage.enterShippingAddress(super.testData);
	}

	@When("^I select door to door shipping method$")
	public void selectSelectDoorToDoor() throws Throwable {
		shippingPage = new ShippingPage(super.seleniumDriver);
		shippingPage.selectShippingMethod(super.testData);
	}
	
	@When("^I select credit/debit card payment option$")
	public void selectPaymentOption() throws Throwable {
		shippingPage = new ShippingPage(super.seleniumDriver);
		shippingPage.enterPaymentDetails(super.testData);
	}
	
	@When("^I place an order$")
	public void placeAnOrder() throws Throwable {
		System.out.println("placeAnOrder()");
	}

	@Then("^I check that the order is listed on the gridview$")
	public void checkGridView() throws Throwable {
		System.out.println("checkGridView()");
	}
	
	@AfterMethod
	@After("@CreditCardPaymentDoorToDoor")
	public void closeBrowser()
	{
		super.closeBrowser();
		htmlReport.SaveHTMLReport();
	}

}
