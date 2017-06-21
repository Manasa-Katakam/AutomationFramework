package com.atm.modulefive.webdriver.advanced.configuration;

import org.openqa.selenium.WebDriver;

/*
 * [IK] Let's use Singleton, and remove everything which is not needed for it.
 * 
 */
public abstract class WebDriverCreator {
	protected WebDriver driver;

	public abstract WebDriver factoryMethod();

}
