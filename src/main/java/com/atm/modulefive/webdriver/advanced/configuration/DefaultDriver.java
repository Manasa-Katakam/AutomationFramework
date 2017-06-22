package com.atm.modulefive.webdriver.advanced.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/*
 * [IK] Let's use Singleton, and remove everything which is not needed for it.
 * [MK] Removed the other Driver intializers and kept the singleton usage
 */
public class DefaultDriver {

	private static WebDriver driver;

	private DefaultDriver() {

	}

	public static WebDriver initializeDriver() {
		if (null == driver) {
			System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			driver = new ChromeDriver(capabilities);
		}
		return driver;
	}

	public static void closeBrowser() {
		if (null != driver) {
			driver.quit();
			driver = null;
		}
	}
}
