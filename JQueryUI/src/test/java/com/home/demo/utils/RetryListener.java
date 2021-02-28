package com.home.demo.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer{

	int count=0;
	public static final int retryCount=2;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<retryCount)
		{
			count++;
			return true;
		}
		return false;
	}

}
