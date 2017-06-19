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

	WebDriver driver = null;
	String filePath = "./evidence/screenshots_new/"; // [IK] Updated the path.
	Logger logger = LogManager.getRootLogger();

	public void onTestStart(ITestResult result) {
		logger.info("*****The name of the testcase Started is :" + result.getName());
		String methodName = result.getName().toString().trim();
		String Name = "Startof" + methodName;
		takeScreenshot(Name);
	}

	public void onTestSuccess(ITestResult result) {
		logger.info("*****The name of the testcase Passed is :" + result.getName());
		String methodName = result.getName().toString().trim();
		String Name = "OnSuccessof" + methodName;
		takeScreenshot(Name);
	}

	public void onTestFailure(ITestResult result) {
		logger.info("*****The name of the testcase Failed is :" + result.getName());
		String methodName = result.getName().toString().trim();
		String Name = "OnFailureof" + methodName;
		takeScreenshot(Name);
	}

	/*
	 * [IK] Added to check screenshots.
	 * 
	 */
	public void takeIntermediateScreenshot(String name) {
		logger.info("Taking screenshot " + name);
		takeScreenshot(name);
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

	private void takeScreenshot(String Name) {
		driver = DefaultDriver.initializeDriver();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filePath + Name + ".png"));
		} catch (IOException e) {
			logger.trace("Error in plcaing the file due to, " + e.getMessage());
		}
	}

}
