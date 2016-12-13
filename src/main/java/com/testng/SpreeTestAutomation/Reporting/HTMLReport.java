package com.testng.SpreeTestAutomation.Reporting;

import com.testng.SpreeTestAutomation.Enummerables.Enums;
import com.testng.SpreeTestAutomation.HelperObjects.Logging;
import com.testng.SpreeTestAutomation.HelperObjects.TestBase;
import com.testng.SpreeTestAutomation.HelperObjects.TestResult;
import com.testng.SpreeTestAutomation.Utilities.SeleniumDriver;

import static com.testng.SpreeTestAutomation.HelperObjects.TestBase.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.joda.time.DateTime;

/**
 *
 * @author OM22054
 */
public class HTMLReport
{
    private String mainHeading;
    private String subHeading;
    private int numberOfTestsExcecuted;
    private int numberOfTestsPassed;
    private int numberOfTestsFailed;
    private long totalRuntime;
    private List<TestResult> testResults;
    
    Logging logger = new Logging();
    
    public HTMLReport() 
    {
         this.mainHeading = "Unknown";
         this.subHeading = "Unknown";
         this.numberOfTestsExcecuted = 0;
         this.numberOfTestsPassed = 0;
         this.numberOfTestsFailed = 0;
         this.totalRuntime = 0;
         this.testResults = new ArrayList<TestResult>();
    }
    
    public void SetMainHeading(String mainHeading)
    {
        this.mainHeading = mainHeading;
    }
    
    public String GetMainHeading()
    {
       return this.mainHeading; 
    }
    
    public void SetSubHeading(String subHeading)
    {
        this.subHeading = subHeading;
    }
    
    public String GetSubHeading()
    {
       return this.subHeading; 
    }

    public String GetDateToString()
    {
        String formatedDate = null;
        
        Date currentDate = GregorianCalendar.getInstance().getTime();
        formatedDate = new DateTime( currentDate ).toString("yyyy-MM-dd HH:mm:ss");
        
        return formatedDate;
    }
    
    public int CalcNumberOfTestsExcecuted()
    {
        this.numberOfTestsExcecuted = testResults.size();
        return this.numberOfTestsExcecuted;
    }
    
    public int AddToNumberOfTestsPassed()
    {
       for(TestResult testResult : this.testResults)
       {
           if(testResult.GetStatus().equals(true))
               this.numberOfTestsPassed += 1;
       }
       
       return this.numberOfTestsPassed;
    }
    
    public int AddToNumberOfTestsFailed()
    {
       for(TestResult testResult: this.testResults)
       {
           if(testResult.GetStatus().equals(false))
               this.numberOfTestsFailed += 1;
       }       
       return this.numberOfTestsFailed;
    }
    
    public String TotalRuntimeToString()
    {
       for(TestResult testResult: this.testResults)
       {
           this.totalRuntime += testResult.GetDuration();   
           System.out.print("Seconds "+TimeUnit.MILLISECONDS.toSeconds(this.totalRuntime));
       }
       
       String formatedDuration = String.format("%02d Hour(s) %02d Minute(s), %02d Second(s)",
               TimeUnit.MILLISECONDS.toHours(this.totalRuntime),
               TimeUnit.MILLISECONDS.toMinutes(this.totalRuntime),
               TimeUnit.MILLISECONDS.toSeconds(this.totalRuntime)/* - 
               TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this.totalRuntime))*/);

        return formatedDuration;
    }
    public void addToTestResult(TestResult testResult)
    {
        if(this.testResults != null)
        {
            this.testResults.add(testResult);
        }

    }
    
    private String GenerateHTML()
    {
        String testMethodLink;
        StringBuilder stringHTMLBuilder = new StringBuilder();
        
        stringHTMLBuilder.append( "<!doctype html>\n" );
        stringHTMLBuilder.append( "<html lang='en'>\n" );

        stringHTMLBuilder.append( "<head>\n" );
        stringHTMLBuilder.append( "<meta charset='utf-8'>\n" );
        stringHTMLBuilder.append("<title style=\"font-family:verdana;\">Automation Test Report - ").append( "</title>\n");
        stringHTMLBuilder.append( "</head>\n\n" );

        stringHTMLBuilder.append( "<body>\n" );
        
        stringHTMLBuilder.append("<h1 style=\"font-family:verdana;\"><a href=\"").append(testResultLocation).append("\">Spree ").append(this.GetMainHeading()).append(environment).append( " Environment</a></h1>\n");
        stringHTMLBuilder.append("<h3 style=\"font-family:verdana;\">Report date - ").append(this.GetDateToString()).append( "</h2>\n");
        //stringHTMLBuilder.append( "<h3>Test statistics</h3>\n" );
        
        stringHTMLBuilder.append( "<table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\" style=\" border-collapse:collapse; font-family:verdana; border:1px solid black\">\n");
        
        stringHTMLBuilder.append( "<tr>\n");   
        stringHTMLBuilder.append( "<td colspan=\"4\" style=\"background-color:#0582BC; font-size: 15pt;color:#ffffff\">Test Statistics</td>\n");
        stringHTMLBuilder.append( "</tr>\n");
        
        stringHTMLBuilder.append( "<tr style=\"outline: thin solid black;\">\n");
        stringHTMLBuilder.append( "<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Total Tests</th>\n");
        stringHTMLBuilder.append( "<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Total Passed</th>\n");
        stringHTMLBuilder.append( "<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Total Failed</th>\n");
        stringHTMLBuilder.append( "<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Total Runtime</th>\n");
        stringHTMLBuilder.append( "</tr>\n");
        
        stringHTMLBuilder.append( "<tr style=\"outline: thin solid black;\">\n");
        stringHTMLBuilder.append("<td style=\"border-left:1px solid black;text-align:center;\">").append(this.CalcNumberOfTestsExcecuted()).append( "</td>\n");
        stringHTMLBuilder.append("<td style=\"border-left:1px solid black;text-align:center;\">").append(this.AddToNumberOfTestsPassed()).append( "</td>\n");
        stringHTMLBuilder.append("<td style=\"border-left:1px solid black;text-align:center;\">").append(this.AddToNumberOfTestsFailed()).append( "</td>\n");
        stringHTMLBuilder.append("<td style=\"border-left:1px solid black;text-align:center;\">").append(this.TotalRuntimeToString()).append( "</td>\n");
        stringHTMLBuilder.append( "</tr>\n");
        
        stringHTMLBuilder.append( "</table>\n");
        
        
        stringHTMLBuilder.append( "<table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\" style=\" border-collapse:collapse;font-family:verdana; border:1px solid black;\">\n");
        
        stringHTMLBuilder.append( "<tr>\n");   
        stringHTMLBuilder.append( "<td colspan=\"5\" style=\"background-color:#0582BC; font-size: 15pt;color:#ffffff\">Results Summary</td>\n");
        stringHTMLBuilder.append( "</tr>\n");
        
        stringHTMLBuilder.append( "<tr style=\"outline: thin solid black;\">\n");
        stringHTMLBuilder.append( "<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Test Case ID</th>\n");
        stringHTMLBuilder.append( "<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Keyword</th>\n");
        stringHTMLBuilder.append( "<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Pass \\ Fail</th>\n");
        stringHTMLBuilder.append( "<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Test Message</th>\n");
        stringHTMLBuilder.append( "<th style=\"border-left:1px solid black;background-color:#B2B2B2;color:#ffffff;\">Test Duration</th>\n");
        stringHTMLBuilder.append( "</tr>\n");
 
        
        for(TestResult testResult: testResults)
        {
            stringHTMLBuilder.append( "<tr style=\"outline: thin solid black;\">\n");
            testMethodLink = testResultLocation + "\\" +testResult.GetTestData().GetTestDataName() + "_" + testResult.GetTestData().GetTestDataID();
               
            if(testResult.GetStatus().equals(true))
            {
                stringHTMLBuilder.append("<td  bgcolor=\"#B2FCA3\" style=\"border-left:1px solid black;\">").append(testResult.GetTestData().GetTestDataID()).append( "</td>\n");
                stringHTMLBuilder.append("<td style=\"border-left:1px solid black;\"><a href = \"").append(testMethodLink).append("\">").append(testResult.GetTestData().GetTestDataName()).append( "</a></td>\n");
                stringHTMLBuilder.append( "<td style=\"border-left:1px solid black;\">Pass</td>\n");
            }
            else
            {
                stringHTMLBuilder.append( "<td  bgcolor=\"#FF9494\" style=\"border-left:1px solid black;\">").append(testResult.GetTestData().GetTestDataID()).append("</td>\n");
                stringHTMLBuilder.append("<td style=\"border-left:1px solid black;\"><a href = \"").append(testMethodLink).append("\">").append(testResult.GetTestData().GetTestDataName()).append( "</a></td>\n");
                stringHTMLBuilder.append( "<td style=\"border-left:1px solid black;\">Fail</td>\n");
            }
            
            stringHTMLBuilder.append("<td style=\"border-left:1px solid black;\">").append(testResult.GetMessage()).append( "</td>\n");
            
            stringHTMLBuilder.append("<td style=\"border-left:1px solid black;\">").append(testResult.DurationToString()).append( "</td>\n");
            stringHTMLBuilder.append( "</tr>\n");
        }
        
        stringHTMLBuilder.append( "</table>\n");
        
        stringHTMLBuilder.append( "</body>\n\n");
        
        stringHTMLBuilder.append( "</html>\n" );
        
        return stringHTMLBuilder.toString();
    }
    public Boolean SaveHTMLReport()
    {
    	try
    	{
	        String html = GenerateHTML();

	    	File dir = new File(testResultLocation);
	    	
	    	if(!dir.exists())
	    		dir.mkdirs();

		      try
		      {
			    	File reportFile = new File(dir,"TestReport.html");

			    	try (Writer writer = new BufferedWriter(new OutputStreamWriter(
			                new FileOutputStream(reportFile), "utf-8"))) {
			    		
			    		writer.write(html);
			  }

		    	  return true;
			  }
			  catch(IOException e)
			  {
			       logger.printLog(Enums.LogType.ERROR, "Unable to write to file. " + e.getMessage());
			       return false;
		  }

    	}
    	catch(Exception e)
    	{
    		logger.printLog(Enums.LogType.ERROR, "Unable to create new directory. " + e.getMessage());
    		return false;
    	}
    }

//   public Boolean SaveHTMLReport()
//   {
//       try
//       {
//            String html = GenerateHTML();
//
//            File reportFile = new File("TestReport.html");
//            FileOutputStream outPutStream = new FileOutputStream(testResultLocation + File.separator + environment + File.separator + release + File.separator +  reportFile);
//            
//            reportFile.createNewFile();
//            
//
//           try (BufferedWriter writer = new BufferedWriter(new FileWriter( testResultLocation + File.separator + environment + File.separator + release + File.separator +  reportFile))) {
//               writer.write(html);
//               
//               System.out.println(html);
//               
//               reportFile = null;
//                      
//               return true;
//           }
//           catch(IOException e)
//           {
//                logging.printLog(Enums.LogType.ERROR, "Unable to write to file. " + e.getMessage());
//                return false;
//           }
//       }
//       catch(Exception e)
//       {
//           logging.printLog(Enums.LogType.ERROR, "Unable to create report file. " +  e.getMessage());
//           return false; 
//       }
//   }

}
