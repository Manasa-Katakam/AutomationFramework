package com.atm.modulefive.webdriver.advanced.configuration;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener, ISuiteListener, IInvokedMethodListener {
	
	WebDriver driver=null;
	String filePath = "./resources/screenshots/";
	Logger logger = LogManager.getRootLogger();
	
	public void onTestStart(ITestResult result) {
		logger.info("*****The name of the testcase Started is :"+result.getName());
	}

	public void onTestSuccess(ITestResult result) {		
		logger.info("*****The name of the testcase Passed is :"+result.getName());
		String methodName=result.getName().toString().trim();
		takeScreenshot(methodName);	
	}

	public void onTestFailure(ITestResult result) {
		logger.info("*****The name of the testcase Failed is :"+result.getName());
		String methodName=result.getName().toString().trim();
		takeScreenshot(methodName);	
	}

	public void onTestSkipped(ITestResult result) {		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {		
	}

	public void onStart(ITestContext context) {	
		
	}

	public void onFinish(ITestContext context) {		
		
	}

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {		
		
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		
	}

	public void onStart(ISuite suite) {		
		
	}

	public void onFinish(ISuite suite) {	
		
	}
	
	private void takeScreenshot(String methodName) {
		driver = DefaultDriver.initializeDriver(); 
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 try {
				FileUtils.copyFile(scrFile, new File(filePath+methodName+".png"));
			} catch (IOException e) {
				logger.trace("Error in plcaing the file due to, "+e.getMessage());
			}
	}

}
