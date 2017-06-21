package com.atm.modulefive.webdriver.advanced.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/*
 * [IK] Let's use Singleton, and remove everything which is not needed for it.
 * 
 */

public class ChromeDriverCreator extends WebDriverCreator {

	public static final String PATH_TO_CHROMEDRIVER = "./libs/chromedriver.exe";

	@Override
	public WebDriver factoryMethod() {
		System.setProperty("webdriver.chrome.driver", PATH_TO_CHROMEDRIVER);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		driver = new ChromeDriver(capabilities);
		return driver;
	}

}
