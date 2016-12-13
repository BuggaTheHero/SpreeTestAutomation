package com.testng.SpreeTestAutomation.PageObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

import com.testng.SpreeTestAutomation.Enummerables.Enums.LocatorType;
import com.testng.SpreeTestAutomation.Enummerables.Enums.LogType;
import com.testng.SpreeTestAutomation.Enummerables.Enums.MenMenu;
import com.testng.SpreeTestAutomation.Enummerables.Enums.NewMenu;
import com.testng.SpreeTestAutomation.Enummerables.Enums.WomenMenu;
import com.testng.SpreeTestAutomation.HelperObjects.Item;
import com.testng.SpreeTestAutomation.HelperObjects.TestBase;
import com.testng.SpreeTestAutomation.Utilities.SeleniumDriver;

public class LandingPage extends TestBase {

    
    public LandingPage(SeleniumDriver seleniumDriver) 
    {
    	super(seleniumDriver);
    }


	public String PopUpCSS() {
		return "#newsletter-sign-up > a > img";
	}

	public String SearchTextBoxID() {
		return "q";
	}

	public String NewMenuItemXpath() {
		return "/*[@id=\"dropdown_1\"]/div/ul/li[1]/a/span";
	}

	public String WomenMenuItemXpath() {
		return "/*[@id=\"dropdown_1\"]/div/ul/li[2]/a/span";
	}

	public String MenMenuItemXpath() {
		return "/*[@id=\"dropdown_1\"]/div/ul/li[3]/a/span";
	}

	public String KidsMenuItemXpath() {
		return "//*[@id=\"dropdown_1\"]/div/ul/li[4]/a/span";
	}

	public String BrandsMenuItemXpath() {
		return "/*[@id=\"dropdown_1\"]/div/ul/li[5]/a/span";
	}

	public String InspirationMenuItemXpath() {
		return "/*[@id=\"dropdown_1\"]/div/ul/li[6]/a/span";
	}

	public String OutletMenuItemXpath() {
		return "/*[@id=\"dropdown_1\"]/div/ul/li[7]/a/span";
	}

	public static String popUpCloseClassName() {
		return "close";
	}

	public static String addToCartButtonClassName() {
		return "add-to-cart";
	}
	
	public static String addToCartXpath() {
		return "//*[@id=\"product_addtocart_form\"]/div[2]/button";
	}

	public static String CheckOutButtonID() {
		return "checkout_link";
	}

	public static String AddShippingAddressButtonID() {
		return "shipping-address-add";
	}

	public static String MenuXpath(int number) {
		return "//*[@id=\"menu\"]/ul/li["+number+"]";
	}

	public static String SubMenuXpath(int number) {
		return "//*[@id=\"menu\"]/ul/li["+number+"]/div/ul/li[1]/div/ul/li[2]/a";
	}

	//Item details selectors
	public static String firstItemOnGridXpath(int number) {
		return "/html/body/div[2]/section/div[1]/div[1]/div[2]/ul/li["+number+"]/div/div[2]/a[1]";
	}
	
	public static String itemBrandXpath() {
		return "//*[@ itemprop ='brand']";
	}
	
	public static String itemTitleXpath() {
		return "//*[@ itemprop ='name']";
	}

	public static String itemSizeXpath() {
		return "//*[@ class = 'size-select ']//ul//li[1]";
	}
	
	public static String itemPriceXpath() {
		return "//*[@ itemprop ='price']";
	}

	public static String Search()
	{
		return "search";
	}
	
	Item item = new Item();
	
	public LandingPage selectCategory() 
	{
		
		try
		{
			List<String> categories =  new ArrayList<>(Arrays.asList("NEW","WOMEN","MEN"));

			List<LocatorType> locatorTypes = new ArrayList<LocatorType>();
			locatorTypes.add(LocatorType.LINK_TEXT);
			locatorTypes.add(LocatorType.LINK_TEXT);
			
			List<String> values = new ArrayList<String>();
			
			Random randomGenerator = new Random();
			int menuPosition = randomGenerator.nextInt(2);
			int subMenuPosition = 0;
			int subMenuChildPosition = 0;
			
			super.seleniumDriver.ClickElementByClassName(popUpCloseClassName());
			
			switch(menuPosition) 
			{
				//Excecuted if 0 is generated for the "NEW" menu item on the front end
				case 0:
					values.add(categories.get(menuPosition));
					subMenuPosition = randomGenerator.nextInt(NewMenu.values().length-1);

					for(NewMenu a: NewMenu.values())
					{
						if(a.getPosition() == subMenuPosition)
						{
							subMenuChildPosition = randomGenerator.nextInt(a.getDescription().length-1);
							values.add(a.getDescription()[subMenuChildPosition]);
						}
					}
					break;
					//Excecuted if 1 is generated for the "Women" menu item on the front end
				case 1:
					
					subMenuPosition = randomGenerator.nextInt(WomenMenu.values().length-1);
					values.add(categories.get(menuPosition));
					
					for(WomenMenu a: WomenMenu.values())
					{
						if(a.getPosition() == subMenuPosition)
						{
							subMenuChildPosition = randomGenerator.nextInt(a.getDescription().length-1);
							values.add(a.getDescription()[subMenuChildPosition]);
						}
					}
					break;
					//Excecuted if 0 is generated for the "MEN" menu item on the front end
				case 2:
					subMenuPosition = randomGenerator.nextInt(MenMenu.values().length-1);
					values.add(categories.get(menuPosition));
					
					for(MenMenu a: MenMenu.values())
					{
						if(a.getPosition() == subMenuPosition)
						{
							subMenuChildPosition = randomGenerator.nextInt(a.getDescription().length-1);
							values.add(a.getDescription()[subMenuChildPosition]);
						}
					}
					break;
			}

			// Hovers over menu and clicks sub menu on the landing page
		    seleniumDriver.HoverOverElementAndClickSubElement(locatorTypes, values);
		    
		    values.clear();
		    locatorTypes.clear();
		    
		    return new LandingPage(seleniumDriver);
		}
		catch(Exception e)
		{
			return new LandingPage(null);
		}

	}

	public LandingPage selectARandomItem()
	{
		Random randomGenerator = new Random();
		int secondValue;
	    boolean itemSelected = false;
	    boolean itemInStock = true;
	    int itemListSize;
	    
	    super.seleniumDriver.Pause(2000);
	    
	    itemListSize =  (int) super.seleniumDriver.getUnOrderedListSize(LocatorType.XPATH, "//*[@class='products product-listing-desktop']//li");

	    System.out.println("itemListSize " + itemListSize);
	    
	    secondValue = randomGenerator.nextInt(itemListSize-1) + 1;
	    
	    itemSelected = super.seleniumDriver.ClickElementByXpath("//*[@class='products product-listing-desktop']//li["+secondValue+"]/div/div[2]//a[1]");

	    if(itemSelected)
	    {
	    	itemInStock = selectSize();
	    	
			while(itemInStock  == false)
			{
			    secondValue = randomGenerator.nextInt(itemListSize-1) + 1;
			    
			    super.seleniumDriver.backToPreviousPage();
			    
			    itemSelected = super.seleniumDriver.ClickElementByXpath("//*[@class='products product-listing-desktop']//li["+secondValue+"]/div/div[2]//a[1]");
				
				super.seleniumDriver.Pause(2000);

				itemInStock = selectSize();

			}
			
			if(itemInStock)
			{
				super.seleniumDriver.Pause(2000);

		        item.setBrandName(seleniumDriver.GetElementText(LocatorType.XPATH, itemBrandXpath()));
		        item.setDescription(seleniumDriver.GetElementText(LocatorType.XPATH, itemTitleXpath()));
		        item.setSize(seleniumDriver.GetElementText(LocatorType.XPATH, itemSizeXpath()));
		        String newPrice = seleniumDriver.GetElementText(LocatorType.XPATH,itemPriceXpath()).substring(1).replaceAll(",","");
		        item.setPrice(Double.parseDouble(newPrice));
		        item.items(item);
			}
	    }
	    else
	    	logger.printLog(LogType.INFO, "Unable to select an item. Please investigate...");
		
		return new LandingPage(seleniumDriver);
	}
	
	public boolean selectSize() 
	{
		boolean sizeFound = false;
		
		int unOrderedListSize =  super.seleniumDriver.getSizeOfAllElements(LocatorType.XPATH, "//*[@id='size-select']//li");
		
		
	    for(int position = 0; position < unOrderedListSize; position++)
	    {
	    	sizeFound = super.seleniumDriver.selectElementInUnOrderedList(LocatorType.XPATH, "//*[@id='size-select']//li");
	        
	    	if(sizeFound)
	    	{
				clickOnAddToCartButton();
				
				super.seleniumDriver.Pause(2000);
				
				//Checks for "This product is currently out of stock." message.
		        if(!super.seleniumDriver.checkIfElementIsVisible(LocatorType.XPATH,"//*[@id=\"product_addtocart_form\"]/div[1]/div[3]"))
		        {
		        	sizeFound = true;
		        	logger.printLog(LogType.INFO, "Selected size: " + item.getSize()); 
		        	break;
		        }
		        else
		        	sizeFound = false;
	    	}
	 
	    }
	    
	    	return sizeFound;
	}

	public void clickOnAddToCartButton() 
	{
		seleniumDriver.ClickElementByXpath(LandingPage.addToCartXpath());
		seleniumDriver.Pause(2000);

	}
	
	public LandingPage checkThatTotalsArePopulatedCorrectly()
	{
		try
		{
			List<LocatorType> locatorTypes = new ArrayList<LocatorType>();
			List<String> values = new ArrayList<String>();
			
			String sQuantity = seleniumDriver.GetWebDriver().findElement(By.className("count cart_count")).getText().substring(1,2);
			int iQuantity = Integer.parseInt(sQuantity);
			
	        locatorTypes.add(LocatorType.CLASS_NAME);
	        locatorTypes.add(LocatorType.CLASS_NAME);
	        
            values.add("count cart_count");
            values.add("subtotal float-right");
            
	        double dPrice = 0.0;
	        double dTotal = 0.0;
	        
	        System.out.println("itemCount: " + iQuantity);
	        
			if(iQuantity < 1)
			{
				logger.printLog(LogType.INFO, "Item quantity needs to be more then 0.");
				return new LandingPage(null);
			}
			else
			{
		        for(int counter = 1; counter <= iQuantity; counter++)
		        {
		            String price = seleniumDriver.GetSubElementTextOnHover(locatorTypes,values);
		            price = price.substring(1);
		            System.out.println("Amount: " + price);
		            
		            dPrice += Double.parseDouble(price);
		        }
			}
    
	        return new LandingPage(seleniumDriver);
		}
	    catch(Exception e)
		{
	    	return new LandingPage(null);
		}
	}

    public LandingPage addItemToCart()
    {
    	navigatetoLandingPage();
		selectCategory();
		selectARandomItem();
		checkThatTotalsArePopulatedCorrectly();
		
		return new LandingPage(seleniumDriver);
    }
    
    public void checkOut()
    {
    	super.seleniumDriver.ClickElementByID(CheckOutButtonID());
    }
    
	public LandingPage navigatetoLandingPage(String url)
	{
		this.seleniumDriver.GetWebDriver().navigate().to(url);
		
		return new LandingPage(seleniumDriver);
	}
	
	public LandingPage navigatetoLandingPage()
	{
		this.seleniumDriver.GetWebDriver().navigate().to("https://elastic.spreeza.net/");
		
		return new LandingPage(seleniumDriver);
	}
	
	public LoginRegisterPage clickLoginRegisterLink() {

		return new LoginRegisterPage(seleniumDriver);
	}

}
