package com.home.demo.pages;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.home.demo.loggers.LoggerClass;
import com.home.demo.utils.*;


public class BaseClass{
  
	
  
  public Boolean elementDisplayed(WebElement element)
  {
	  Boolean flag=false;
	try
	{
		
	
	  if(element.isDisplayed())
		  flag=true;
	}
	catch(NoSuchElementException e)
	{
		LoggerClass.error("Element is not present");
		
		
	}
	return flag;
  }
  
  
  public void launchURL(String url,WebDriver driver)
  {
	  driver.get(Configurator.getInstance().getProperties(url));
  }
 }
