package com.atm.modulefive.webdriver.advanced.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * [IK] Let's use Singleton, and remove everything which is not needed for it.
 * 
 */
public class FirefoxDriverCreator extends WebDriverCreator {

	public static final String PATH_TO_GECKODRIVER = "./libs/geckodriver.exe";

	@Override
	public WebDriver factoryMethod() {
		System.setProperty("webdriver.gecko.driver", PATH_TO_GECKODRIVER);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

}
