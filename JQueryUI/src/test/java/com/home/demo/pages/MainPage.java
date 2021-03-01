package com.home.demo.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath="//a[contains(text(),'Selectable')]")
	WebElement selectable;
	
	@FindBy(xpath="//iframe[@class='demo-frame']")
	WebElement iframe;
	
	@FindBy(xpath="//oi[@class='ui-selectable']//li[@class='ui-widget-content ui-selectee']")
	List<WebElement> listToBeSelected;
	@FindBy(id="select-result")
	WebElement checkText;
	By listSelected=By.xpath("//oi[@class='ui-selectable']//li[@class='ui-widget-content ui-selectee ui-selected']");
	WebDriver driver;
	
	public MainPage(WebDriver driver)
	
	{
		this.driver=driver;
		
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
	 public Boolean Selectable() throws Exception
	 {
		 Boolean flag=false;
		 try {
			 br.elementToClick(selectable, driver);
			 driver.switchTo().frame(iframe);
			 
			    if(!driver.findElements(listSelected).isEmpty())
               {
			   List<WebElement> elements=driver.findElements(listSelected);
			        for(WebElement element:elements)
			        {
			        	element.click();
			        }
			   
			    }else
			    {      
			          WebElement start=listToBeSelected.get(1);
			          WebElement end=listToBeSelected.get(3);
			          Actions action=new Actions(driver);
			    	  Action act=action.clickAndHold(start).moveToElement(end).build();
			    	  act.perform();
			    	  String text=driver.findElement(listSelected).getText();
			    	  if(text.contains(checkText.getText().trim().replaceFirst("#","")))
			    			  
			    	  {
			    		  flag=true;
			    	  }
			    	  
			      }
			    return flag;
			    }
		 catch(Exception e)
		   
		  {
				LoggerClass.error(e.getMessage());
				throw e;
			}
	 }

	
}
