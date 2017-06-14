package com.atm.modulefive.webdriver.advanced.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.atm.modulefive.webdriver.advanced.utils.ActionUtility;

public class VoloTeaSignIn {

    private WebDriver driver;

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

    public VoloTeaUserProfile doLogin(String email, String password) throws InterruptedException {
	ActionUtility.waitForSync();
	ActionUtility.waitForElementClickable(driver, 6, LINK_SIGNIN);
	ActionUtility.jsClick(driver, LINK_SIGNIN);
	// LINK_SIGNIN.click();
	System.out.println("Clicked on Sign-In Link On Home Page");
	System.out.println("Typing user login: " + email);
	INPUT_EMAIL.clear();
	INPUT_EMAIL.sendKeys(email);
	System.out.println("Typing user password: " + password);
	INPUT_PASSWORD.clear();
	INPUT_PASSWORD.sendKeys(password);
	BUTTON_SIGNIN.click();
	System.out.println("Login is in progress...");
	return new VoloTeaUserProfile(driver);
    }

}
