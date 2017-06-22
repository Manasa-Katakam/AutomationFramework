package com.atm.modulefive.webdriver.advanced.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.atm.modulefive.webdriver.advanced.configuration.CustomListener;
import com.atm.modulefive.webdriver.advanced.configuration.DefaultDriver;
import com.atm.modulefive.webdriver.advanced.testdata.User;
import com.atm.modulefive.webdriver.advanced.utils.ActionUtility;

public class VoloTeaSignIn {

	CustomListener listener = new CustomListener();

	// [IK] Better use singleton [MK] Done
	WebDriver driver = DefaultDriver.initializeDriver();
	Logger logger = LogManager.getRootLogger();

	public VoloTeaSignIn(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a.switcherLogin")
	private WebElement LINK_SIGNIN;

	@FindBy(id = "emailLoginForm")
	private WebElement INPUT_EMAIL;

	@FindBy(id = "passwordLoginForm")
	private WebElement INPUT_PASSWORD;

	@FindBy(xpath = "//form[@id='loginForm']//a[contains(@class,'voloteaButton')]")
	private WebElement BUTTON_SIGNIN;

	public VoloTeaUserProfile doLogin(User user) throws InterruptedException {
		ActionUtility.waitForSync();
		ActionUtility.waitForElementClickable(driver, 6, LINK_SIGNIN);
		ActionUtility.jsClick(driver, LINK_SIGNIN);
		logger.info("Clicked on Sign-In Link On Home Page");
		logger.info("Typing user login: " + user.getEmail());
		ActionUtility.waitForSync();
		INPUT_EMAIL.clear();
		INPUT_EMAIL.sendKeys(user.getEmail());
		logger.info("Typing user password: " + user.getPassword());
		INPUT_PASSWORD.clear();
		INPUT_PASSWORD.sendKeys(user.getPassword());
		BUTTON_SIGNIN.click();
		logger.info("Login is in progress...");
		listener.takeIntermediateScreenshot("Test 3"); // [IK] Added to check
														// screenshots
		return new VoloTeaUserProfile(driver);
	}

}
