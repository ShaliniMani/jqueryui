package com.home.demo.utils;


import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.home.demo.loggers.LogMessage;
import com.home.demo.pages.BaseClass;



public class TestLogListener  implements  ITestListener
{
	WebDriver driver;
	ExtentReports extents=ExtentReportClass.getExtentReport();
	ExtentTest test;
	//In case of parallel execution,the Threadlocal will privde a thread safe excecution
	private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	private Logger getLogger(String className){
	 Logger log = LogManager.getLogger(className);

		return log;
		
	}

	public void onFinish(ITestContext context) {
		Logger logger = getLogger(context.getSuite().getName());
		logger.info("******Suite Completed********");
	    extents.flush();
	}
	
	/**
	 * This method copies provided driver to temp folder, loads framework and application configuration.
	 * It also logs to DB based on useReportDB value. By default it logs to report database.
	 */

	public void onStart(ITestContext context) {
		
		Logger logger = getLogger(context.getSuite().getName());
		logger.info("********Suite Started********");	
		
		}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}
	

	
	public void onTestSkipped(ITestResult result) {
		Logger logger = getLogger(result.getTestClass().getName() + "." + result.getMethod().getMethodName());
		flushLogMessages(result,logger);
		logger.info("********Skipped*******");
		

	}
	
	public void onTestStart(ITestResult result) {
		
		Logger logger = getLogger(result.getTestClass().getName() + "." + result.getMethod().getMethodName());
		logger.info("********Started*******");
		test=extents.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
	}
	
	public void onTestSuccess(ITestResult result) {
		updateTestResult(result,"Passed");
		extentTest.get().log(Status.PASS, result.getMethod().getMethodName()+" is passed");
		
	}
	
	public void onTestFailure(ITestResult result){
		
		
		extentTest.get().fail(result.getThrowable());
try {
	extentTest.get().addScreenCaptureFromPath(ScreenShotUtility.getScreenshotPath(result.getMethod().getMethodName(),getDriver(result)),result.getMethod().getMethodName());
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (Throwable e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
updateTestResult(result,"Failed");

	}
	
	
	
	private void updateTestResult(ITestResult result, String message) 
	{
		
		String testName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
		Logger logger = getLogger(testName);
		flushLogMessages(result,logger);
		logger.info(message);
		logger.info("test time: " + (result.getEndMillis() - result.getStartMillis()));
		
			}
	private WebDriver getDriver(ITestResult result) throws Throwable
	{
		driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		return driver;
	}
	
	private synchronized void flushLogMessages(ITestResult result, Logger logger) {	
		
		
		
		List<LogMessage> logMessages = com.home.demo.loggers.LoggerClass.getLogMessages(result);
		
			for(LogMessage logMessage: logMessages)
			{
			switch (logMessage.getLevel()){
			case "DEBUG":
				logger.debug(logMessage.getMessage());
				break;
			case "WARN":
				logger.warn(logMessage.getMessage());
				break;
			case "INFO":
				logger.info(logMessage.getMessage());
				break;
			case "ERROR":
				logger.error(logMessage.getMessage());
				break;
			case "TRACE":
				logger.trace(logMessage.getMessage());
				break;
			}
          }
	}
}
	
	

    
    
    
    

