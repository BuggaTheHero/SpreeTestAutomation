package com.testng.SpreeTestAutomation.PageObjects;

import com.testng.SpreeTestAutomation.Enummerables.Enums.LocatorType;
import com.testng.SpreeTestAutomation.HelperObjects.TestBase;
import com.testng.SpreeTestAutomation.HelperObjects.TestData;
import com.testng.SpreeTestAutomation.Utilities.SeleniumDriver;

public class LoginRegisterPage extends TestBase {
	
	public LoginRegisterPage(SeleniumDriver seleniumDriver)
	{
	     super(seleniumDriver);
	}
	
	//Existing customers
	public String existingCustomerEmailTextBoxID() {
		return "email";
	}

	public String existingCustomerPasswordTextBoxID() {
		return "pass";
	}
	
	//New customers
	public String firstNameTextBoxID() {
		return "0firstname";
	}
	
	public String lastNameTextBoxID() {
		return "0lastname";
	}
	
	public String newCustomerEmailTextBoxID() {
		return "email_address";
	}
	
	public String dd_DOBTextBoxID() {
		return "day";
	}
	
	public String mm_DOBTextBoxID() {
		return "month";
	}
	
	public String yyyy_DOBTextBoxID() {
		return "year";
	}
	
	public String maleRadioButtonID() {
		return "gender-1";
	}
	
	public String femaleRadioButtonID() {
		return "gender-2";
	}
	
	
	public String newCustomerPasswordTextBoxID() {
		return "password";
	}
	
	public String confirmPasswordTextBoxID() {
		return "confirmation";
	}
	
	public String loginButtonID() {
		return "send2";
	}
	
	public String registerButtonID() {
		return "submit-register";
	}
	
	public String LoginRegisterClassName()
	{
		return "icon-account-empty";
	}
	
	public LoginRegisterPage Login(TestData testData)
	{
		super.seleniumDriver.ClickElementByClassName(LoginRegisterClassName());
		super.seleniumDriver.Enter_Text(LocatorType.ID, existingCustomerEmailTextBoxID(), testData.GetValueFromTestData("ExistingEmail"));
		super.seleniumDriver.Enter_Text(LocatorType.ID, existingCustomerPasswordTextBoxID(), testData.GetValueFromTestData("ExistingPassword"));
		
		super.seleniumDriver.ClickElementByID(loginButtonID());
		
		return new LoginRegisterPage(super.seleniumDriver);
	}
	
	public LoginRegisterPage register(TestData testData)
	{
		String pageSource = null;
		 java.util.Date date = new java.util.Date();
				 
		super.seleniumDriver.ClickElementByClassName(LoginRegisterClassName());
		super.seleniumDriver.Enter_Text(LocatorType.ID, firstNameTextBoxID(), testData.GetValueFromTestData("Firstname"));
		super.seleniumDriver.Enter_Text(LocatorType.ID, lastNameTextBoxID(), testData.GetValueFromTestData("Lastname"));
		super.seleniumDriver.Enter_Text(LocatorType.ID, newCustomerEmailTextBoxID(),date.getTime() + "_" + testData.GetValueFromTestData("Email") );
		super.seleniumDriver.Enter_Text(LocatorType.ID, dd_DOBTextBoxID(), testData.GetValueFromTestData("DD"));
		super.seleniumDriver.Enter_Text(LocatorType.ID, mm_DOBTextBoxID(), testData.GetValueFromTestData("MM"));
		super.seleniumDriver.Enter_Text(LocatorType.ID, yyyy_DOBTextBoxID(), testData.GetValueFromTestData("YYYY"));
		super.seleniumDriver.ClickElementByID(maleRadioButtonID());
		super.seleniumDriver.Enter_Text(LocatorType.ID, newCustomerPasswordTextBoxID(), testData.GetValueFromTestData("NewPassword"));
		super.seleniumDriver.Enter_Text(LocatorType.ID, confirmPasswordTextBoxID(), testData.GetValueFromTestData("ConfirmPassword"));
		super.seleniumDriver.ClickElementByID(registerButtonID());
		
		super.seleniumDriver.Pause(2000);
		
		pageSource = super.seleniumDriver.GetPageSource();
		
		if(pageSource.contains("There is already an account"))
		{
			Login(testData);
		}

		super.seleniumDriver.Pause(5000);
		
		return new LoginRegisterPage(super.seleniumDriver);
	}

}
