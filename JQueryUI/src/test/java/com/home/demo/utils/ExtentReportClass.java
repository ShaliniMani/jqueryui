package com.home.demo.utils;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportClass {

	static ExtentReports extent;
	
	public static ExtentReports getExtentReport()
	{
	String path=System.getProperty("user.dir")+"//report//index.html";
	ExtentSparkReporter report=new ExtentSparkReporter(path);
	report.config().setDocumentTitle("JQueryUIAutomation");
	report.config().setReportName("FunctionalReport");
	report.config().setTheme(Theme.DARK);
	
	extent=new ExtentReports();
	extent.attachReporter(report);
	extent.setSystemInfo("TesterName","shalini");
	extent.setSystemInfo("OS", "Windows 7");
	
	
	return extent;
}
}
