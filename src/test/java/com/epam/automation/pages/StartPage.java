package com.epam.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.automation.model.Constants;
import com.epam.automation.tests.WebDriverInitialize;

public class StartPage {

    @FindBy(xpath = "//a[text()='Sign in']")
    private WebElement signInButton;

    WebDriver driver = WebDriverInitialize.getWebDriverInstance();

    public StartPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }

    public void openGitHubURL() {
	driver.get(Constants.getGithubBaseUrl());
    }

    public SignInPage invokeSignIn() {
	signInButton.click();
	return new SignInPage(driver);
    }
}
