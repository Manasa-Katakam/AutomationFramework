package com.Reporting;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestNGITestListener implements ITestListener {

	private static ExtentReports extent = ExtentManager.createInstance("extent.html");
	private static ThreadLocal parentTest = new ThreadLocal();
	private static ThreadLocal test = new ThreadLocal();

	public synchronized void onTestStart(ITestResult result) {
		ExtentTest child = ((ExtentTest) parentTest.get()).createNode(result.getMethod().getMethodName());
		test.set(child);
	}

	public synchronized void onTestSuccess(ITestResult result) {
		((ExtentTest) test.get()).pass("Test passed");

	}

	public synchronized void onTestFailure(ITestResult result) {
		try {
			((ExtentTest) test.get()).fail(result.getThrowable()).addScreenCaptureFromPath("Screenshot.png");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("The excepyion for saving the screenshot is: " + e);
		}

	}

	public synchronized void onTestSkipped(ITestResult result) {
		((ExtentTest) test.get()).skip(result.getThrowable());

	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public synchronized void onStart(ITestContext context) {
		ExtentTest parent = extent.createTest(getClass().getName());
		parentTest.set(parent);

	}

	public synchronized void onFinish(ITestContext context) {
		extent.flush();

	}

}
