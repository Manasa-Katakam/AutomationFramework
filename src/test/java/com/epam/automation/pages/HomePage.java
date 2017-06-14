package com.epam.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.automation.tests.WebDriverInitialize;

public class HomePage {

    @FindBy(xpath = "//button[@aria-label='Switch account context']/span")
    private WebElement linkLoggedInUser;

    WebDriver driver = WebDriverInitialize.getWebDriverInstance();

    public HomePage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }

    public String getLoggedInUserName() {
	return linkLoggedInUser.getText();
    }

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
