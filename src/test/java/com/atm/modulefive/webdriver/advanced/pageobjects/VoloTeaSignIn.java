package com.atm.modulefive.webdriver.advanced.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.atm.modulefive.webdriver.advanced.testdata.User;
import com.atm.modulefive.webdriver.advanced.utils.ActionUtility;

public class VoloTeaSignIn extends VoloTeaAbstract {

    public VoloTeaSignIn(WebDriver driver) {
	super(driver);
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
	System.out.println("Clicked on Sign-In Link On Home Page");
	System.out.println("Typing user login: " + user.getEmail());
	ActionUtility.waitForSync();
	INPUT_EMAIL.clear();
	INPUT_EMAIL.sendKeys(user.getEmail());
	System.out.println("Typing user password: " + user.getPassword());
	INPUT_PASSWORD.clear();
	INPUT_PASSWORD.sendKeys(user.getPassword());
	BUTTON_SIGNIN.click();
	System.out.println("Login is in progress...");
	return new VoloTeaUserProfile(driver);
    }

}
