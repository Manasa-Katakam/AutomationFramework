package com.epam.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.automation.model.Constants;

public class StartPage {

	@FindBy(xpath = "//a[text()='Sign in']")
	private WebElement signInButton;
	
	private final WebDriver driver; // [IK] Use already initialized WebDriver.
	
//	WebDriver driver  = WebDriverInitialize.getWebDriverInstance(); // [IK] Here it is.

	public StartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void openGitHubURL() {
		driver.get(Constants.GITHUB_BASE_URL);
	}

	public SignInPage invokeSignIn() {
		signInButton.click();
		return new SignInPage(driver);
	}
}
