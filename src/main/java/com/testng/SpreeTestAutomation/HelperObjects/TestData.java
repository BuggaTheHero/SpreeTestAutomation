package com.testng.SpreeTestAutomation.HelperObjects;

import java.util.HashMap;
import java.util.Map;

import com.testng.SpreeTestAutomation.Enummerables.Enums;

/**
 *
 * @author OM22054
 */
public class TestData 
{
    private String status;
    private String testDataID;
    private String testDataName;
    private Map<String, String> keyValuePair;
    
    Logging logging = new Logging();
    
    public TestData()
    {
        keyValuePair = new HashMap<String, String>();
    }
    
    public void SetStatus(String status)
    {
        this.status = status;
    }
    public String GetStatus()
    {
        return this.status;
    }
        
    public void SetTestDataID(String testDataID)
    {
        this.testDataID = testDataID;
    }
    
    public String GetTestDataID()
    {
        return this.testDataID;
    }

    public void SetTestDataName(String testDataName)
    {
        this.testDataName = testDataName;
    }
    public String GetTestDataName()
    {
        return this.testDataName;
    }
    
    public void AddToTestData(String key, String value)
    {
        if(keyValuePair != null)
        {
            this.keyValuePair.put(key, value);
        }
        else
           logging.printLog(Enums.LogType.INFO, "Unable to add key/value to testData. Key: " + key + " Value: " + value);
    }
    
    public String GetValueFromTestData(String key)
    {
        if(keyValuePair != null && keyValuePair.containsKey(key))
        {
           return keyValuePair.get(key);
        }
        
        return null;
    }
    
    public void UpdateValueFromTestData(String key, String value)
    {
        if(keyValuePair != null && keyValuePair.containsKey(key))
        {
            this.keyValuePair.put(key, value);
        }
        else
            logging.printLog(Enums.LogType.INFO, "Unable to update testData.");
            
    }
    
    public Map<String, String>  GetKeyValuePairList()
    {
        if(keyValuePair != null)
        {
            return keyValuePair;
        }
        else
            return null;
        
    }
    
}

