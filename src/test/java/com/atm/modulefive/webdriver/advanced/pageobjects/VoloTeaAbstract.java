package com.atm.modulefive.webdriver.advanced.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.atm.modulefive.webdriver.advanced.configuration.DefaultDriver;

public class VoloTeaAbstract {

 // [IK] Use singleton here. The code is commented below.
    WebDriver driver = DefaultDriver.initializeDriver();

    public VoloTeaAbstract(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }

    public boolean isElementPresent(By loacator) {
	return driver.findElements(loacator).size() > 0;
    }

    public String getElementText(By locator) {
	return driver.findElement(locator).getText();
    }
}
