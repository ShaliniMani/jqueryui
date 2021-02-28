package com.home.demo.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.home.demo.loggers.LoggerClass;
import com.home.demo.pages.*;
import com.home.demo.utils.DriverManager;



public class MainPageTest {

	DriverManager dr=new DriverManager();
	MainPage mp;
    public WebDriver driver;
	
	MainPageTest()
	{
		driver=dr.getDriver();
		mp=PageFactory.initElements(driver, MainPage.class);
		
	}
	@Test
	public void verifyContributeTest()
	{
		
		
		try
		{

		Boolean flag=mp.verifyContributeTab();
		Assert.assertTrue(flag);
		
	}
		catch(Exception e)
		{

			LoggerClass.error(e.getMessage());
			Assert.fail("Failed in"+ Thread.currentThread().getStackTrace()[1] 
            .getMethodName());
			
		}
	}
	@AfterTest
	public void tearDown()
	{
		dr.quitDriver();
	}
	
}
