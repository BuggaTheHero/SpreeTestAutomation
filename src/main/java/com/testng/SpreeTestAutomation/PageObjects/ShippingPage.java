package com.testng.SpreeTestAutomation.PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.testng.SpreeTestAutomation.Enummerables.Enums.LocatorType;
import com.testng.SpreeTestAutomation.Enummerables.Enums.LogType;
import com.testng.SpreeTestAutomation.HelperObjects.TestBase;
import com.testng.SpreeTestAutomation.HelperObjects.TestData;
import com.testng.SpreeTestAutomation.Utilities.SeleniumDriver;

public class ShippingPage extends TestBase 
{
	
	public ShippingPage(SeleniumDriver seleniumDriver)
	{
	     super(seleniumDriver);
	}
	
	public String firstNameTextBoxID() {
		return "firstname";
	}
	
	public String lastNameTextBoxID() {
		return "lastname";
	}
	
	public String contactNumberTextBoxID() {
		return "contact_number";
	}
	
	public String streetTextBoxID() {
		return "street";
	}
	
	public String suburbTextBoxID() {
		return "suburb";
	}
	
	public String cityTextBoxID() {
		return "city";
	}
	
	public String suburbQueryTextBoxID()
	{
		return "suburb_query";
	}
	
	public String postalCodeTextBoxID() {
		return "postal_code";
	}
	
	public String submitAddressButtonXpath()
	{
		return "//*[@id=\"submit-address-button\"]";
	}
	
	public String submitAddressButtonClass()
	{
		return "submit-form";
	}
	
	public String addAddressButtonID()
	{
		return "add_address_button";
	}
	
	public String creditOrDebitRadioButtonXpath()
	{
		return "//*[@id=\"payment-options\"]/section/form/label[1]/i";
	}
	
	public String pargoShippingMethodClass()
	{
		return "option_pargo_bestway";
	}
	
	public String doorToDoorShippingMethodClass()
	{
		return "option_doortodoor_bestway";
	}
	
	public String cardNameTextboxID()
	{
		return "card_name";
	}
	
	public String cardNumberextboxID()
	{
		return "card_number";
	}
	
	public String cardExpiryMonthTextboxID()
	{
		return "card_expiry_month";
	}
	
	public String cardExpiryYearTextboxID()
	{
		return "card_expiry_year";
	}
	
	public String cvvTextboxID()
	{
		return "card_cvv";
	}
	
	public ShippingPage enterShippingAddress(TestData testData)
	{
		LandingPage landingPage = new LandingPage(super.seleniumDriver);
		landingPage.checkOut();
		
		super.seleniumDriver.Pause(5000);
		super.seleniumDriver.Enter_Text(LocatorType.ID, contactNumberTextBoxID(), testData.GetValueFromTestData("ContactNumber"));
		super.seleniumDriver.Enter_Text(LocatorType.ID, streetTextBoxID(), testData.GetValueFromTestData("Street"));
		super.seleniumDriver.ClickElementByID(suburbTextBoxID());
		super.seleniumDriver.Enter_Text(LocatorType.ID, suburbQueryTextBoxID(), testData.GetValueFromTestData("Suburb"));
		super.seleniumDriver.Pause(2000);
		super.seleniumDriver.ClickElementByID("address_16769");
		
		
		super.seleniumDriver.ClickElementByXpath(submitAddressButtonXpath());

		return new ShippingPage(super.seleniumDriver);
	}
	
	public ShippingPage selectShippingMethod(TestData testData)
	{
		 List<WebElement> elements = null;
		 WebElement element = null;
		
		super.seleniumDriver.Pause(5000);

		
		switch(testData.GetValueFromTestData("ShippingMethod"))
		{
			case "Pargo":
			{
				super.seleniumDriver.ClickElementByClassName(pargoShippingMethodClass());
				
				super.seleniumDriver.Pause(10000);
				
				//Select a pargo point
				super.seleniumDriver.GetWebDriver().switchTo().frame("pargo-selector-iframe");
				super.seleniumDriver.ClickElementByXpath("//*[@id=\"points_map\"]/div/div/div[9]/div[1]/div/div[3]");
				
				super.seleniumDriver.Pause(2000);
				
				elements = super.seleniumDriver.getListAllElements(LocatorType.ID, "//*[@id='points_list']//div//h4");
				
				if(elements.size() > 0)
				{
					element = elements.get(1);
				    element.click();
				}
				
				super.seleniumDriver.Pause(4000);
				break;
			}
			case "DoorToDoor":
			{
				super.seleniumDriver.ClickElementByClassName(doorToDoorShippingMethodClass());
				enterPaymentDetails(testData);
				break;
			}
			default:
				logger.printLog(LogType.INFO, "Shipping method not present in input file");
			
		}
		
		return new ShippingPage(super.seleniumDriver);
	}
	
	public ShippingPage enterPaymentDetails(TestData testData)
	{
		super.seleniumDriver.Pause(2000);
		
		switch(testData.GetValueFromTestData("PaymentOption"))
		{
			case "CreditDebit":
			{
				super.seleniumDriver.ClickElementByXpath(creditOrDebitRadioButtonXpath());
				super.seleniumDriver.Enter_Text(LocatorType.ID, cardNameTextboxID(), testData.GetValueFromTestData("CardName"));
				super.seleniumDriver.Enter_Text(LocatorType.ID, cardNameTextboxID(), testData.GetValueFromTestData("CardNumber"));
				super.seleniumDriver.Enter_Text(LocatorType.ID, cardExpiryMonthTextboxID(), testData.GetValueFromTestData("CardExpiryMonth"));
				super.seleniumDriver.Enter_Text(LocatorType.ID, cardExpiryYearTextboxID(), testData.GetValueFromTestData("CardExpiryMonth"));
				super.seleniumDriver.Enter_Text(LocatorType.ID, cardNameTextboxID(), testData.GetValueFromTestData("CVV"));

				super.seleniumDriver.ClickElementByXpath("//*[@id='use-card-button']");
				
				break;
			}
			case "EFT":
			{
				super.seleniumDriver.ClickElementByID(creditOrDebitRadioButtonXpath());
				break;
			}
			case "Instant EFT":
			{
				super.seleniumDriver.ClickElementByID(creditOrDebitRadioButtonXpath());
				break;
			}
			case "SnapScan":
			{
				super.seleniumDriver.ClickElementByID(creditOrDebitRadioButtonXpath());
				break;
			}
		}
		return new ShippingPage(super.seleniumDriver);
	}

}
