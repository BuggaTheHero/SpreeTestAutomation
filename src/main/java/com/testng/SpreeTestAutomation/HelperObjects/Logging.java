package com.testng.SpreeTestAutomation.HelperObjects;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.testng.SpreeTestAutomation.Enummerables.Enums.LogType;

public class Logging 
{
    private LogType logType;
    private String message;
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
    public Logging()
    {
		  FileAppender fileAppender = new FileAppender();
		  fileAppender.setName("FileLogger");
		  fileAppender.setFile("mylog.log");
		  fileAppender.setLayout(new PatternLayout ("%d %-5p [%c{1}] %m%n"));
		  fileAppender.setThreshold(Level.INFO);
		  fileAppender.setAppend(true);
		  fileAppender.activateOptions();

		  //add appender to any Logger (here is root)
		  Logger.getRootLogger().addAppender(fileAppender);
		  //repeat with all other desired appenders
        
    }
    
    public void printLog(LogType logtype, String message)
    {
        this.logType = logtype;
        this.message = message;
        
        switch(this.logType)
        {
            case ALL:
            {
                break;
            }
            case DEBUG:
            {
                this.message = message;
                logger.debug(this.message);
                System.out.println(this.message);
                break;
            }
            case ERROR:
            {
                this.message = message;
                logger.error(this.message);
                System.out.println(this.message);
                break;
            }
            case FATAL:
            {
                this.message = message;
                logger.fatal(this.message);
                System.out.println(this.message);
                break;
            }
            case INFO:
            {
                this.message = message;
                logger.info(this.message);
                System.out.println(this.message);
                break;
            }
            case TRACE:
            {
                this.message = message;
                logger.trace(this.message);
                break;
            }
            case WARN:
            {
                this.message = message;
                logger.warn(this.message);
                break;
            }
            case OFF:
			break;
			
		default:
			break;
        }

    }

}