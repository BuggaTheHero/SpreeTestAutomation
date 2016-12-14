package com.testng.SpreeTestAutomation.HelperObjects;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.testng.SpreeTestAutomation.Enummerables.Enums;
import com.testng.SpreeTestAutomation.Enummerables.Enums.LogType;
import com.testng.SpreeTestAutomation.ReadInputData.ExcelWorkbook;
import com.testng.SpreeTestAutomation.Reporting.HTMLReport;
import com.testng.SpreeTestAutomation.Utilities.SeleniumDriver;

public class TestBase {
	
    public static String testResultLocation;
    public static String environment;
    public static String release;
    
	protected SeleniumDriver seleniumDriver;
	protected Properties configFile;
	protected static FileInputStream inputStream;
	protected ExcelWorkbook excelWorkbook = new ExcelWorkbook();;
	public static HTMLReport htmlReport;
    protected TestData testData = new TestData();
	protected List<TestData> testDataList = new ArrayList<TestData>();
	protected TestResult testResult;
	public Logging  logger = new Logging();;
	
	public TestBase ()
	{
	}
	
	public TestBase (SeleniumDriver seleniumDriver)
	{
		this.seleniumDriver = seleniumDriver;
	}
	
    public boolean loadConfigFile()
    {
        try
        {
	       this.configFile = new Properties();
	       inputStream = new FileInputStream(new File("src/main/java/config.properties"));
	       configFile.load(inputStream);
	       
	       return true;

        }
        catch(Exception e)
        {
            logger.printLog(Enums.LogType.ERROR, "LoadConfigFile() " + e.getMessage());
            return false;
        }
    }
    
    public boolean loadTestDataList(String inputFile)
    {
        try
        {
            if(this.testDataList.isEmpty())
            {
                this.testDataList = excelWorkbook.ReadExcelWorkbook(inputFile);
                return true;
            }
            else 
            	return true;
        
        }
        catch(Exception e)
        {
        	logger.printLog(Enums.LogType.ERROR, "ReadExcelWorkbook() " + e.getMessage());
            return false;
        }
    }
    
	public boolean setupTest(String inputFileName, String testName)
	{
		try{
			
			//Load the config file and test data list from inputfile
			loadConfigFile();
			loadTestDataList(configFile.getProperty(inputFileName));

			//Retrieve the test data belonging to this test
			testData = getTestData(testName);	
			
			//Populate test report variables
			String userDir = System.getProperty("user.dir");
			environment = configFile.getProperty("Environment");
			release = configFile.getProperty("Release");
			testResultLocation = userDir + File.separator + "Test Results" 
										 + File.separator+ environment 
										 + File.separator + release 
										 + File.separator + inputFileName; 
			
	        if(htmlReport == null)
	        {
	        	htmlReport = new HTMLReport();
	            htmlReport.SetMainHeading("Automation Test Report : ");
	        }
	        
			//Start webDriver
	        if(seleniumDriver == null)
	        	seleniumDriver = new SeleniumDriver();
	        	//seleniumDriver = new SeleniumDriver("Dependents//chromedriver");
			
			//Start new test
	        testResult = new TestResult();
			testResult.startTime();
			testResult.SetTestData(this.testData);
			
			return true;

      	}
		catch(Exception e)
		{
			logger.printLog(LogType.ERROR, "setupTest() " + e.getMessage());
			return false;
		}
	}
    
	public void closeBrowser()
	{
        seleniumDriver.closeBrowser();
		System.out.println("Duration: " + testResult.DurationToString());
	}
	
    public TestData getTestData(String testName)
    {
    	try
    	{
			for(TestData tData: testDataList)
			{ 
				if(tData.GetTestDataName().trim().equals(testName))	
				{
					testData = tData;
				}
			}
			
			return testData;
    	}
    	catch(Exception e)
    	{
    		logger.printLog(LogType.ERROR, "Unable to complete getTestData() method");
    		return testData;
    	}
    }

    public void endTest()
    {
		testResult.setStatus(true);
		testResult.endTime();
		
		htmlReport.addToTestResult(testResult);
		
		if(testResult.GetStatus())
			testResult.setMessage("Completed succesfully");
		else
			testResult.setMessage("Completed unsuccessfully");
    }

}
