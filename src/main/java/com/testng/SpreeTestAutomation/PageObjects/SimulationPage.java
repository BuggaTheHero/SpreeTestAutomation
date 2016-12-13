package com.testng.SpreeTestAutomation.PageObjects;

import com.testng.SpreeTestAutomation.Enummerables.Enums.LocatorType;
import com.testng.SpreeTestAutomation.HelperObjects.TestBase;
import com.testng.SpreeTestAutomation.Utilities.SeleniumDriver;

public class SimulationPage extends TestBase
{
	public SimulationPage(SeleniumDriver seleniumDriver)
	{
	     super(seleniumDriver);
	}
	
	private String expectedReturnCodeID()
	{
		return "returnCode";
	}
	
	private String submitButtonXpath()
	{
		return "//*[@id=\"frameDiv\"]/table/tbody/tr[18]/td[1]/input";
	}
	
	private String resetButtonXpath()
	{
		return "//*[@id=\"frameDiv\"]/table/tbody/tr[18]/td[2]/input";
	}
	
	public void selectExpectedReturnCode(String text)
	{
		super.seleniumDriver.Select_From_Dropdown(LocatorType.ID, expectedReturnCodeID(), text);
	}
	
	public void submit()
	{
		super.seleniumDriver.ClickElementByXpath(submitButtonXpath());
	}
	
	
	public void reset()
	{
		super.seleniumDriver.ClickElementByXpath(resetButtonXpath());
	}
}
