package com.atm.modulefive.webdriver.advanced.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class VoloTeaAbstract {

	protected WebDriver driver;

	public VoloTeaAbstract(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public boolean isElementPresent(By loacator) {
		return driver.findElements(loacator).size() > 0;
	}
}
