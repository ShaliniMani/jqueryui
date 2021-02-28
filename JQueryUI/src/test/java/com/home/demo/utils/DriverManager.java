package com.home.demo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;

public class DriverManager {
	
    
	private  ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	WebDriver driver;
	
	public  void initialize()
	{
		
		System.out.println("inside driver manager");
		String browser=System.getProperty("browsername");
		//String browser="chrome";
		if(browser.toLowerCase().contains("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",Configurator.getInstance().getProperties("chromedriver"));
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(browser.toLowerCase().contains("firefox"))
		{

			System.setProperty("webdriver.gecko.driver",Configurator.getInstance().getProperties("geckodriver"));
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
		}
		setWebDriver(driver);
		}
	public void setWebDriver(WebDriver driver1)
	{
        webDriver.set(driver1);

	}
	
	public WebDriver getDriver()
	{
		initialize();
		System.out.println("inside get driver");
		return webDriver.get();
	}
	
	public  void quitDriver()
	{
		if(webDriver!=null)
		{
			webDriver.get().quit();
		}
	}
}
