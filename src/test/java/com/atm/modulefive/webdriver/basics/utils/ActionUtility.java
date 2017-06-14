package com.atm.modulefive.webdriver.basics.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ActionUtility {

    public static void waitForElementClickable(WebDriver driver, int timeout, final By by) {
	new WebDriverWait(driver, timeout).pollingEvery(6, TimeUnit.SECONDS)
		.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementVisible(WebDriver driver, int timeout, final By by) {
	new WebDriverWait(driver, timeout).pollingEvery(6, TimeUnit.SECONDS)
		.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForPageLoaded(WebDriver driver) {
	ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
	    public Boolean apply(WebDriver driver) {
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
			.equals("complete");
	    }
	};
	try {
	    Thread.sleep(3000);
	    WebDriverWait wait = new WebDriverWait(driver, 30);
	    wait.until(expectation);
	} catch (Throwable error) {
	    Assert.fail("Timeout waiting for Page Load Request to complete.");
	}
    }

    public static boolean isElementPresent(WebDriver driver, By by) {
	return !driver.findElements(by).isEmpty();
    }

    public static String getElementValue(WebDriver driver, By by) {

	return driver.findElement(by).getText();

    }
}
