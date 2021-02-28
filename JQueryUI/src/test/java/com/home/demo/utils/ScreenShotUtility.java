package com.home.demo.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtility {
	
	public static String getScreenshotPath(String testCaseName,WebDriver driver) throws IOException
	  {
		  TakesScreenshot ts=(TakesScreenshot)driver;
		  File source=ts.getScreenshotAs(OutputType.FILE);
		  String destPath=System.getProperty("user.dir")+"\\report\\"+testCaseName+".png";
		  File fi=new File(destPath);
		  FileUtils.copyFile(source,fi);
		  return destPath;
		  
		  
	  }
}
