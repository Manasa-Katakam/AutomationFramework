package com.epam.automation.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverInitialize {
    private static WebDriver driver;

    private WebDriverInitialize() {
    }

    public static WebDriver getWebDriverInstance() {
	if (null == driver) {
	    driver = new FirefoxDriver();
	}
	return driver;
    }

    public static void closeWebBrowser() {
	driver.quit();
	driver = null;
    }
}
