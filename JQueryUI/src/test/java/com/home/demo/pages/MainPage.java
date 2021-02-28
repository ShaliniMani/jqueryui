package com.home.demo.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.home.demo.loggers.LoggerClass;
import com.home.demo.utils.Configurator;
import com.home.demo.utils.DriverManager;




public class MainPage {
 BaseClass br=new BaseClass();
	
	
	@FindBy(xpath="//*[@href='https://contribute.jquery.org/']")
	WebElement contributeTab;
	
	@FindBy(xpath="//*[@href='https://contribute.jquery.org/triage/']")
	WebElement triage;
	WebDriver driver;
	public MainPage(WebDriver driver)
	{this.driver=driver;
		
		System.out.println("inside main page");	
		
		
	}
	
	 public Boolean verifyContributeTab() throws Exception
	  {
		 try
		 {
	     br.launchURL("mainurl",this.driver);
		 	 
		 if(contributeTab.isDisplayed())
		 {
		  Actions act=new Actions(driver);
		  Action action=act.moveToElement(contributeTab).build();
		  action.perform();
		  LoggerClass.info("Contribute tab is present..");
		  return br.elementDisplayed(triage);
		 }
		 else
		 {
			LoggerClass.info("Contribute tab is not present..");
			throw new NoSuchElementException("Contribute tab is not present..");
		 }
		 }
		 catch(Exception e)
	   
		  {
				LoggerClass.error(e.getMessage());
				throw e;
			}
	  }

	
}
