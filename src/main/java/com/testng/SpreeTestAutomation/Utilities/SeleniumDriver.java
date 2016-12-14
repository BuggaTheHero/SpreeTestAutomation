package com.testng.SpreeTestAutomation.Utilities;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.gargoylesoftware.htmlunit.javascript.host.Map;
import com.testng.SpreeTestAutomation.Enummerables.Enums.BrowserType;
import com.testng.SpreeTestAutomation.Enummerables.Enums.LocatorType;
import com.testng.SpreeTestAutomation.Enummerables.Enums.LogType;
import com.testng.SpreeTestAutomation.HelperObjects.TestBase;

import org.openqa.selenium.JavascriptExecutor;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.URL;
/**
 *
 * @author OM22054
 */
public class SeleniumDriver extends TestBase
{
    
    public static final String USERNAME = "spree3";
    public static final String AUTOMATE_KEY = "coFkpCcqaYrspUvJTdoM";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
  
    private WebDriver webDriver;
    private final File browserDriverFile;
    private final BrowserType browserType;
    private boolean browserStarted = false;
       
    public SeleniumDriver(String driverLocation) throws Exception
    {
        this.browserDriverFile = new File(driverLocation);

        this.browserType = BrowserType.Chrome;
         
        System.setProperty("webdriver.chrome.driver", this.browserDriverFile.getAbsolutePath());
    }
    
    public SeleniumDriver() throws Exception
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "55.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "7");
        caps.setCapability("browserstack.debug", "true");
        caps.isJavascriptEnabled();
        
        webDriver = new RemoteWebDriver(new URL(URL), caps);
        this.browserType = null;
        this.browserDriverFile = new File(" ");
    }

	public boolean startWebDriver()
    {
        if(this.webDriver == null)
        {
            switch (browserType) 
            {
                case IE: 
                {
                    this.webDriver  =  new InternetExplorerDriver();
                    this.browserStarted = true;
                    break;
                }
                case FireFox : 
                {
                    this.webDriver  = new FirefoxDriver();
                    this.browserStarted = true;
                    break;
                }
                case Chrome:
                {

                	HashMap<String, Object> prefs = new HashMap<String, Object>();
                	prefs.put("profile.default_content_setting_values.notifications", 2);
                	ChromeOptions chromeOptions = new ChromeOptions();
                	
                	chromeOptions.setExperimentalOption("prefs", prefs);
                	chromeOptions.addArguments("--kiosk");
                	
                	this.webDriver = new ChromeDriver(chromeOptions);
                    this.browserStarted = true;
                    break;
                }
                case Safari:
                {
                    this.webDriver = new SafariDriver();
                    this.browserStarted = true;
                    break;   
                }
                case Headless:
                {
                    this.webDriver = new HtmlUnitDriver();
                    this.browserStarted = true;
                    break;   
                }
            }
        }  

        this.webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.webDriver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
        this.webDriver.manage().window().maximize();
        
        logger.printLog(LogType.INFO,"WebDriver successfully started");
        
        return this.browserStarted;

    }
    public void NavigateToPage(String url) throws TimeoutException
    {
        try
        {
            webDriver.navigate().to(url); 
            logger.printLog(LogType.INFO,"NavigateToPage (" + url + ")");
            

        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"NavigateToPage (" + url + ") " + e.getMessage());
            
            throw(e);
        }
    }
    
    public WebElement FindWebElement(LocatorType locatorType, String value) 
    		throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException
    {
        try
        {
            By locator = LocatorValue(locatorType, value);
            WebElement element = webDriver.findElement(locator);

            return element;
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }
    }

    public boolean CheckIfElementExist(LocatorType locatorType, String value) 
    		throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException
    {
    	try
    	{
            Pause(2000);
            By locator = LocatorValue(locatorType, value);
            List<WebElement> element = webDriver.findElements(locator);
            
            if(!element.isEmpty())
                return true;
            else 
                return false;
    	}
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }
            
    }
    
    public boolean checkIfElementIsVisible(LocatorType locatorType, String value) 
    		throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException
    {
    	try
    	{
            By locator = LocatorValue(locatorType, value);
            WebElement element = webDriver.findElement(locator);

            if(element.isDisplayed())
                return true;
            else 
                return false;
            
    	}
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible" + e.getMessage());
            throw(e);
        }
            
    }
    public boolean Enter_Text(LocatorType locatorType, String value, String text) 
    		throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException
    {
        try 
        {
            By locator = LocatorValue(locatorType, value);
            WebElement element = webDriver.findElement(locator);

            element.clear();
            
            logger.printLog(LogType.INFO,"Enter_Text (" + text + ")");
            element.sendKeys(text);

            return true;
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + value + " \n" + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + value + " \n" + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + value + " \n" + e.getMessage());
            throw(e);
        }
    }
    
    public boolean ClickElement(WebElement element) 
    		throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException
    {
        try
        {
        	logger.printLog(LogType.INFO,"ClickElement (" + element.getTagName() + ")");
            element.click();
            
            return true;
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + element.getTagName() + " \n"  + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + element.getTagName() + " \n" + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + element.getTagName() + " \n"  + e.getMessage());
            throw(e);
        }
    }

    public boolean ClickElementByID(String id) 
    		throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException
    {
        try
        {
        	logger.printLog(LogType.INFO,"ClickElementByID (" + id + ")");
            webDriver.findElement(By.id(id)).click();
            
            return true;
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + id + " \n" + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + id + " \n" + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + id + " \n" + e.getMessage());
            throw(e);
        }
    }
    
    public boolean ClickElementByName(String name) throws 
    	NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException
    {
        try
        {
        	logger.printLog(LogType.INFO,"ClickElementByName (" + name + ")");
            webDriver.findElement(By.name(name)).click();
            
            return true;
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + name + " \n" + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + name + " \n" + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + name + " \n" + e.getMessage());
            throw(e);
        }
    } 
    
    public boolean ClickElementByClassName(String className) throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException
    {
        try
        {
        	logger.printLog(LogType.INFO,"ClickElementByClassName (" + className + ")");
            webDriver.findElement(By.className(className)).click();
            
            return true;
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + className + " \n" + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + className + " \n" + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + className + " \n" + e.getMessage());
            throw(e);
        }
    }
    
    public boolean ClickElementByCssSelector(String cssSelector) throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException
    {
        try
        {
            webDriver.findElement(By.cssSelector(cssSelector)).click();
            logger.printLog(LogType.INFO,"ClickElementByCssSelector (" + cssSelector + ")");
            return true;
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }
    }
    
    public boolean ClickElementByIDUsingJavaScript(String id) throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException
    {
        try
        {
            webDriver.findElement(By.id(id));
            
            WebElement element = this.webDriver.findElement (By.id(id));
            JavascriptExecutor executor = (JavascriptExecutor) this.webDriver;
            executor.executeScript ("arguments[0].click();" , element);
            
            logger.printLog(LogType.INFO,"ClickElementByIDUsingJavaScript(" + id + ")");
            return true;
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }
    }
    
    public boolean ClickElementByXpath(String Xpath) throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException
    {
        try
        {
            webDriver.findElement(By.xpath(Xpath)).click();
            logger.printLog(LogType.INFO,"ClickElementByXpath (" + Xpath + ")");
            return true;
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }
    }
    
    public boolean ClickElementByLinkText(String LinkText) throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException, ElementNotSelectableException
    {
        try
        {
            webDriver.findElement(By.linkText(LinkText)).click();
            logger.printLog(LogType.INFO,"ClickElementByLinkText (" + LinkText + ")");
            return true;
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }  
        catch(ElementNotSelectableException e)
        {
            logger.printLog(LogType.ERROR,"Element deems to be disabled: " + e.getMessage());
            throw(e);
        }  
    }
    public boolean ClickElementByPartialLinkText(String partialLinkText) throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException, ElementNotSelectableException
    {
        try
        {
            webDriver.findElement(By.partialLinkText(partialLinkText)).click();
            logger.printLog(LogType.INFO,"ClickElementByLinkText (" + partialLinkText + ")");
            return true;
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }  
        catch(ElementNotSelectableException e)
        {
            logger.printLog(LogType.ERROR,"Element deems to be disabled: " + e.getMessage());
            throw(e);
        }  
    }
    
    public boolean Select_From_Dropdown(com.testng.SpreeTestAutomation.Enummerables.Enums.LocatorType locatorType, String value, String text) 
    		throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException, ElementNotSelectableException
    {
        try 
        {
            By locator;
            locator = LocatorValue(locatorType, value);
            WebElement element = webDriver.findElement(locator);

            Select dropDownList = new Select(element);

            dropDownList.selectByValue(text);

            logger.printLog(LogType.INFO,"Select_From_Dropdown (" + value + ")");
            return true;
        } 
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }  
        catch(ElementNotSelectableException e)
        {
            logger.printLog(LogType.ERROR,"Element deems to be disabled: " + e.getMessage());
            throw(e);
        } 

    }
    

    public boolean HoverOverElementAndClickSubElement(List<LocatorType> locatorType, List<String> values)  
    		throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException, ElementNotSelectableException
    {    
        try
        {
            By locator;
            locator = LocatorValue(locatorType.get(0), values.get(0)); 

            Pause(2000);
            Actions action = new Actions(webDriver);       
            WebElement menuHoverLink = webDriver.findElement(locator);        
            action.moveToElement(menuHoverLink).build().perform();       
            logger.printLog(LogType.INFO,"HoverOverElementAndClickSubElement (" + values.get(0) + ")");
            
            Pause(2000);
            locator = LocatorValue(locatorType.get(1), values.get(1));
            action = new Actions(webDriver); 
            menuHoverLink = webDriver.findElement(locator);
            action.moveToElement(menuHoverLink).build().perform();  

            action.moveToElement(menuHoverLink); 
            action.click();        
            action.perform(); 
            logger.printLog(LogType.INFO,"HoverOverElementAndClickSubElement (" + values.get(1) + ")");
            
            return true; 
            
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotSelectableException e)
        {
            logger.printLog(LogType.ERROR,"Element deems to be disabled: " + e.getMessage());
            throw(e);
        } 
   
    }
    
    public boolean HoverOverElementAndClickSubElementCSS(List<LocatorType> locatorType, List<String> values)  
    		throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException, ElementNotSelectableException  
    {    
        try
        {
            By locator;
            locator = LocatorValue(locatorType.get(0), values.get(0)); 

            Actions action = new Actions(webDriver);       
            WebElement menuHoverLink = webDriver.findElement(locator);   
            System.out.println(menuHoverLink.getText());
            action.moveToElement(menuHoverLink);       

            WebElement subMenuHoverLink = webDriver.findElement(locator);
            action.moveToElement(subMenuHoverLink).click().build().perform();  

            return true; 
            
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotSelectableException e)
        {
            logger.printLog(LogType.ERROR,"Element deems to be disabled: " + e.getMessage());
            throw(e);
        } 
   
    }
    public String GetSubElementTextOnHover(List<LocatorType> locatorTypes, List<String> values)
    		throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException, ElementNotSelectableException 
    {
        try
        {
        
            By locator = LocatorValue(locatorTypes.get(0), values.get(0)); 

            Actions action = new Actions(webDriver);       
            WebElement menuHoverLink = webDriver.findElement(locator);        
            action.moveToElement(menuHoverLink).build().perform();       

            locator = LocatorValue(locatorTypes.get(1), values.get(1));
            WebElement subLink = menuHoverLink.findElement(locator);  
        
        
            return subLink.getText();
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotSelectableException e)
        {
            logger.printLog(LogType.ERROR,"Element deems to be disabled: " + e.getMessage());
            throw(e);
        } 
    }
    
    public boolean selectElementInUnOrderedList(LocatorType locatorType, String value)
    		throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException, ElementNotSelectableException 
    {
    	try
    	{
    		boolean found = false;
    		
	        By locator = LocatorValue(locatorType, value);     
	        List<WebElement>  allElements = webDriver.findElements(locator);
	        WebElement element;
	
	    	for (int counter = 1; counter <= allElements.size(); counter++)
			{
	    		element = allElements.get(counter-1).findElement(By.xpath("//*[@id=\"size-select\"]/li["+counter+"]/input"));

	    		if(element.isEnabled())
	    		{
	    			element = allElements.get(counter-1).findElement(By.xpath("//*[@id=\"size-select\"]/li["+counter+"]/label"));
	    			
	    		    System.out.println("Clicked: " + element.getText());
	    		    
	    		    found = true;
	    	        element.click();
	    	        
	    	      break;
	    		}

	    	}
	    	
	    	return found;
	    	
    	}
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotSelectableException e)
        {
            logger.printLog(LogType.ERROR,"Element deems to be disabled: " + e.getMessage());
            throw(e);
        } 
    }
    
    public int getSizeOfAllElements(LocatorType locatorType, String value)
    		throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException, ElementNotSelectableException 
    {
    	try
    	{
	        By locator = LocatorValue(locatorType, value);     
	        List<WebElement>  allElements = webDriver.findElements(locator);
	    	
	    	return allElements.size();
	    	
    	}
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotSelectableException e)
        {
            logger.printLog(LogType.ERROR,"Element deems to be disabled: " + e.getMessage());
            throw(e);
        } 
    }
    
    public  List<WebElement> getListAllElements(LocatorType locatorType, String value)
    		throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException, ElementNotSelectableException 
    {
    	try
    	{
	        By locator = LocatorValue(locatorType, value);     
	        List<WebElement>  allElements = webDriver.findElements(locator);
	    	
	    	return allElements;
	    	
    	}
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotSelectableException e)
        {
            logger.printLog(LogType.ERROR,"Element deems to be disabled: " + e.getMessage());
            throw(e);
        } 
    }
    
    public String GetElementText(LocatorType locatorType, String value)
    		throws NoSuchElementException, StaleElementReferenceException, ElementNotVisibleException, ElementNotSelectableException 
    {
        try
        {
            By locator = LocatorValue(locatorType, value);     
            WebElement subLink = webDriver.findElement(locator);  
            
            return subLink.getText();
        }
        catch(NoSuchElementException e)
        {
            logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
            throw(e);
        }
        catch(StaleElementReferenceException e)
        {
            logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotVisibleException e)
        {
            logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
            throw(e);
        }
        catch(ElementNotSelectableException e)
        {
            logger.printLog(LogType.ERROR,"Element deems to be disabled: " + e.getMessage());
            throw(e);
        } 
    }
        
    public By LocatorValue(LocatorType locatorType, String value) 
    {
        By by;
        switch (locatorType) 
        {
            case ID:
                    by = By.id(value);
                    break;
            case NAME:
                    by = By.name(value);
                    break;
            case XPATH:
                    by = By.xpath(value);
                    break;
            case CSS:
                    by = By.cssSelector(value);
                    break;
            case LINK_TEXT:
                    by = By.linkText(value);
                    break;
            case PARTIAL_LINK_TEXT:
                    by = By.partialLinkText(value);
                    break;
            case CLASS_NAME:
                    by = By.className(value);
                    break;
            default:
                    by = null;
                    break;
        }
        return by;
    }
    
    public int getUnOrderedListSize(LocatorType locatorType, String value)
    {
		try
	    {
			  int counter = 0;
			
			  By locator = LocatorValue(locatorType, value);     
			  List<WebElement>  allElements = webDriver.findElements(locator);

			  for (WebElement element: allElements) 
			  {	
				  counter++;
			  }
			  
			  return counter;
		    	
	    }
	    catch(NoSuchElementException e)
	    {
	          logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
	          throw(e);
	          
	     }
	     catch(StaleElementReferenceException e)
	     {
	          logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
	          throw(e);
	     }
	     catch(ElementNotVisibleException e)
	     {
	         logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
	         throw(e);
	     }
	     catch(ElementNotSelectableException e)
	     {
	         logger.printLog(LogType.ERROR,"Element deems to be disabled: " + e.getMessage());
	         throw(e);
         } 
    }
    
    public String getRandomItemInUnOrderedList(LocatorType locatorType, String value)
    {
		try
	    {
			By locator = LocatorValue(locatorType, value);     
			List<WebElement>  allElements = webDriver.findElements(locator);
				
			  for (WebElement element: allElements) 
			  {
			    element = element.findElement(By.tagName("li"));
			    return element.getText();
			    
			  }
			  
		  return "Not found";
		    	
	    }
	    catch(NoSuchElementException e)
	    {
	          logger.printLog(LogType.ERROR,"Unable to find element: " + e.getMessage());
	          throw(e);
	     }
	     catch(StaleElementReferenceException e)
	     {
	          logger.printLog(LogType.ERROR,"Element not available in the DOM: " + e.getMessage());
	          throw(e);
	     }
	     catch(ElementNotVisibleException e)
	     {
	         logger.printLog(LogType.ERROR,"Element not visible: " + e.getMessage());
	         throw(e);
	     }
	     catch(ElementNotSelectableException e)
	     {
	         logger.printLog(LogType.ERROR,"Element deems to be disabled: " + e.getMessage());
	         throw(e);
         } 
    }
   
    public boolean WaitForElement(org.openqa.selenium.WebElement element) throws TimeoutException, InterruptedException
    {
        boolean elementFound = false;
        int maxWaitTime = 60;
        int waitCount = 0;
		while(!elementFound && waitCount < maxWaitTime)
		{
		    //if(webDriver.findElement(element))
		        
		    elementFound = true;

		    Thread.sleep(500);
		    waitCount ++;
		}
		
		return elementFound;
            
    }

    public String GetPageSource()
    {
    	try
    	{
	       return webDriver.getPageSource();
    	}
    	catch(Exception e)
    	{
            logger.printLog(LogType.ERROR,"WaitForElement (" + e.getMessage() + ")");
            return "No page source";
    	}
    }
    
    public void Pause(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        }
        catch(Exception e)
        {
             logger.printLog(LogType.ERROR,"Pause (" + e.getMessage() + ")");
        }      
    }
    
    public WebDriver GetWebDriver()
    {
    	try
    	{
	    	if(this.webDriver != null)
	    		return this.webDriver;
	    	else
	    	{
	    		startWebDriver();
	    		return this.webDriver;
	    	}
    	}
    	catch(Exception e)
    	{
    		logger.printLog(LogType.ERROR,"GetWebDriver (" + e.getMessage() + ")");
    		return this.webDriver;
    	}
    }
    public void makeElementVisible()
    {
		JavascriptExecutor executor = (JavascriptExecutor)webDriver;
		executor.executeScript("document.getElementById('shipping_option_doortodoor_bestway').style.display='block';");
    }
    
    public void refresh()
    {
    	this.webDriver.navigate().to(this.webDriver.getCurrentUrl());
    }
    
    public void backToPreviousPage()
    {
    	Pause(2000);
    	JavascriptExecutor js = (JavascriptExecutor) this.webDriver; 
    	js.executeScript("window.history.go(-1)");
    }
    
    public void closeBrowser() 
    {
    	if(webDriver != null)
            webDriver.quit();
    	else
    		logger.printLog(LogType.INFO, "The web driver has not been instantiated.");
    }      


}
