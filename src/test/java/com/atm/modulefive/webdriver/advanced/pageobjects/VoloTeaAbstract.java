package com.atm.modulefive.webdriver.advanced.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class VoloTeaAbstract {

	protected WebDriver driver;

		public VoloTeaAbstract(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

	public boolean isElementPresent(By loacator) {
		return driver.findElements(loacator).size() > 0;
	}
	
	public String getElementText(By locator){
		return driver.findElement(locator).getText();
	}
}
