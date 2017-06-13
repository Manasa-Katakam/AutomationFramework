package com.epam.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.automation.tests.WebDriverInitialize;

public class HomePage {

	@FindBy(xpath = "//button[@aria-label='Switch account context']/span")
	private WebElement linkLoggedInUser;

	//	private final WebDriver driver; // [IK] Use already initialized WebDriver.
	
	WebDriver driver  = WebDriverInitialize.getWebDriverInstance(); // [IK] Here it is.
	
	// [MK] commented the unnecessay re-initialised code

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getLoggedInUserName() {
		return linkLoggedInUser.getText();
	}

	/**
	 * [IK] Better use boolean here. 
	 * [MK] Implemented the same with boolean
	 */
	public boolean hasUserLoggedIn() {
		if (linkLoggedInUser.getText() != "") { 
			return true;
		} else {
			return false;
		}
	}

	public WebDriver getDriver() {
		return driver;
	}
}
