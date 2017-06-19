package com.atm.modulefive.webdriver.advanced.utils;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ActionUtility {

	static Logger logger = LogManager.getRootLogger();

	public static void waitForElementClickable(WebDriver driver, int timeout, final WebElement element) {
		new WebDriverWait(driver, timeout).pollingEvery(2, TimeUnit.SECONDS)
				.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForElementToBeVisible(WebDriver driver, int timeout, final WebElement element) {
		new WebDriverWait(driver, timeout).pollingEvery(2, TimeUnit.SECONDS)
				.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForPageLoaded(WebDriver driver) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(10000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Exception to wait for page load or Timeout Exception");
			logger.trace("Exception trace: " + error.getMessage());
		}

	}

	public static void waitForSync() throws InterruptedException {
		Thread.sleep(3000);

	}

	public static String getElementText(WebElement element) {
		return element.getText();
	}

	public static boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	public static void jsClick(WebDriver driver, final WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
}
