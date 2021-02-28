package com.home.demo.utils;

import java.io.FileInputStream;


import java.io.IOException;

import java.util.Properties;


import com.home.demo.loggers.LoggerClass;

public class Configurator {

private static Configurator config=null;
private Properties prop=new Properties();
private final String propertyFilePath= "E:\\JQueryUI\\JQueryUI\\src\\test\\resources\\config.properties";

private Configurator() 
{
	try
	{
	LoggerClass.info("Configuration file to be loaded");
	FileInputStream fin=new FileInputStream(propertyFilePath);
	prop.load(fin);
	LoggerClass.info("Configuration file is loaded");
	
}
catch(IOException excep)
	{
	LoggerClass.info("Configuration file tis not loaded");
	throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);

	}
}
public static synchronized Configurator getInstance(){
    if (config == null)
        config = new Configurator();
    return config;
}

public String getProperties(String property){
	
	String value = this.prop.getProperty(property);
	if(value!= null) 
		return value;
	else 
		throw new RuntimeException(property+ " is not specified in the config.properties file.");	
	
}
}
