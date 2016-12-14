package com.testng.SpreeTestAutomation.Enummerables;

import java.util.List;

public class Enums 
{
    public enum LogType 
    {
        ALL, DEBUG, ERROR, FATAL, INFO, OFF, TRACE, WARN
    }

    public enum BrowserType 
    {
        IE, FireFox, Chrome, Safari, Headless
    }

    public enum LocatorType
    {
        ID, NAME, XPATH, PARTIAL_LINK_TEXT, LINK_TEXT, CSS, CLASS_NAME 
    }  
    
    public enum NewMenu
    {
    	WOMEN (0,new String[]{"Clothing","Shoes","Accessories","Plus Size","Mternity"}),
    	MEN(1,new String[]{"Clothing","Shoes","Accessories","Plus Size","Activewear"}), 
    	KIDS(2,new String[]{"Babies 0-2","Kids 0-8","Kids 8-16"}); 
  
    	private final int position;
    	private final String[] description;
    	 
    	
    	NewMenu(int pos, String[] desc)
    	{
    		this.position = pos;
    		this.description = desc;
    	}
    	
    	public int getPosition() 
    	{                                                                                                
            return position;                                                                                                         
        }
    	
    	public String[] getDescription() 
    	{                                                                                                
            return description;                                                                                                         
        }
    } 
    
    public enum WomenMenu
    {
    	CLOTHING (0,new String[]{"Activewear","Bottom","Coats","Dresses","Jackets & Knitwear","Jeans","Jumpsuits","Maternity","Plus Size","Swimwear","Tops","Wedding"}),
    	SHOES(1,new String[]{"Boots","High Heels","Midi Heels","Pumps","Sandals & Slipons","Sneakers & Lace-Ups","Wedges"}), 
    	ACCESSORIES(2,new String[]{"Belts","Hair Accessories","Handbags","Hats & Gloves","Hosiery","Jewellery","Scarves","Sunglasses","Wallets & Purses","Watches"}),
    	BOUTIQUE (3,new String[]{"Adam & Eve","Colleen Eitzen","David Tlale","ERRE","Gert-Johan Coetzee","Habits","Ilan","ISABEL DE VILLIERS","Judith Atelier","KISUA","Leigh Schubert","Loin Cloth & Ashes","LUMIN","Mille Collines","Rich Mnisi","Rubicon By Hangwani Nengovhela","Ruff Tung","Sober","Somerset Jane","Tamara Chérie","Thula Sindi"});

    	private final int position;
    	private final String[] description;
    	
    	WomenMenu(int pos, String[] desc)
    	{
    		this.position = pos;
    		this.description = desc;
    	}
    	
    	public int getPosition() 
    	{                                                                                                
            return position;                                                                                                         
    	}
    	
    	public String[] getDescription() 
    	{                                                                                                
            return description;                                                                                                         
        }
    } 
    
    public enum MenMenu
    {
    	CLOTHING (0,new String[]{"Casual Pants","Casual Shirts","Formal Pants","Formal Shirts","Golfers","Jackets & Coats","Jeans","Knitwear","Shorts","Suits","Sweats & Hoodies","Swimwear","T-Shirts & Vests"}),
    	SHOES(1,new String[]{"Boots","Formal Shoes","Lace-Ups & Loafers","Pumps","Sandals","Sneakers"}), 
    	ACCESSORIES(2,new String[]{"Bags","Belts","Head & Neckwear","Socks","Sunglasses","Ties & Accessories","Underwear","Wallets","Watches"}),
    	ACTIVEWEAR (3,new String[]{"Accessories","Pants","Shoes","Tops"});

    	private final int position;
    	private final String[] description;
    	
    	MenMenu(int pos, String[] desc)
    	{
    		this.position = pos;
    		this.description = desc;
    	}
    	
    	public int getPosition() 
    	{                                                                                                
            return position;                                                                                                         
    	}
    	
    	public String[] getDescription() 
    	{                                                                                                
            return description;                                                                                                         
        }
    } 
    
    
}
