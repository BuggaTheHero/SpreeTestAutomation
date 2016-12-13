package com.testng.SpreeTestAutomation.HelperObjects;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import java.util.concurrent.TimeUnit;
public class TestResult
{
    private Date date;
    private DateTime startTime;
    private DateTime endTime;
    private long duration;
    private boolean status;
    private TestData testData;
    private String message;
    
    public TestResult()
    {
        testData = null;
        this.date = new Date();
        this.startTime = new DateTime();
        this.endTime = new DateTime();
        this.duration = 0;
        this.status = false;
    }
    
    public TestResult(DateTime startTime, DateTime endTime, boolean status)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
    
    public void SetDate()
    {
        this.date = new Date();
    }
    
    public Date GetDate()
    {
       return this.date; 
    }
    
    public void SetStartTime()
    {
        this.startTime = new DateTime();
    }
    
    public DateTime startTime()
    {
       this.startTime = new DateTime();
       return this.startTime; 
    }
    
    public void SetEndTime()
    {
        this.endTime = new DateTime();
    }
    
    public DateTime endTime()
    {
       this.endTime = new DateTime();
       return this.endTime; 
    }
    
    public long GetDuration()
    {
        Interval interval = new Interval(this.startTime, this.endTime);
        this.duration = interval.toDurationMillis();
        
        return this.duration;
    }
    
    public void setStatus(boolean status)
    {
        this.status = status;
    }
    
    public Boolean GetStatus()
    {
       return this.status; 
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    public String GetMessage()
    {
       return this.message; 
    }
    
    public void SetTestData(TestData testData)
    {
        this.testData = testData;
    }
    
    public TestData GetTestData()
    {
        return this.testData;
    }

    public String DurationToString()
    {
        String formatedDuration = String.format("%02d Hour(s) %02d Minute(s), %02d Second(s)",
               TimeUnit.MILLISECONDS.toHours(GetDuration()),
               TimeUnit.MILLISECONDS.toMinutes(GetDuration()),
               TimeUnit.MILLISECONDS.toSeconds(GetDuration()/*) - 
               TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(GetDuration())*/));

        return formatedDuration;
    }

}
