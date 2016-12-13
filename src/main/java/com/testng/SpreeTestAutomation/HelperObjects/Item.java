package com.testng.SpreeTestAutomation.HelperObjects;

import java.util.ArrayList;
import java.util.List;

public class Item {
	
	private String description;
	private String brandName;
	private double price;
	private double totalPrice;
	private String size;
	
	List<Item> itemList = new ArrayList<Item>();
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setBrandName(String brandName){
		this.brandName = brandName;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public void setSize(String size)
	{
		this.size = size;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getBrandName(){
		return this.brandName;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public String getSize(){
		return this.size;
	}
	
	public List<Item> items(Item item)
	{
		this.itemList.add(item);
		
		return this.itemList;
	}
	
	public double calculateTotal(){
		this.totalPrice += this.price;
		
		return this.totalPrice;
	}

}
