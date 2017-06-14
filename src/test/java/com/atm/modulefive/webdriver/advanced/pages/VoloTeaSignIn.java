package com.atm.modulefive.webdriver.advanced.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.atm.modulefive.webdriver.advanced.utils.ActionUtility;

public class VoloTeaSignIn extends VoloTeaAbstract {

    public VoloTeaSignIn(WebDriver driver) {
	super(driver);
    }

    private final By signin = By.cssSelector("a.switcherLogin");
    private final By emailaddress = By.id("emailLoginForm");
    private final By userpassword = By.id("passwordLoginForm");
    private final By btnSignin = By.xpath("//form[@id='loginForm']//a[contains(@class,'voloteaButton')]");

    public VoloTeaUserProfile doLogin(String email, String password) throws InterruptedException {
	WebElement LINK_SIGNIN = driver.findElement(signin);
	ActionUtility.waitForPageLoaded(driver);
	ActionUtility.waitForElementClickable(driver, 6, LINK_SIGNIN);
	ActionUtility.jsClick(driver, LINK_SIGNIN);
	ActionUtility.waitForPageLoaded(driver);
	System.out.println("Clicked on Sign-In Link On Home Page");
	System.out.println("Typing user login: " + email);
	WebElement INPUT_EMAIL = driver.findElement(emailaddress);
	INPUT_EMAIL.clear();
	INPUT_EMAIL.sendKeys(email);
	System.out.println("Typing user password: " + password);
	WebElement INPUT_PASSWORD = driver.findElement(userpassword);
	INPUT_PASSWORD.clear();
	INPUT_PASSWORD.sendKeys(password);
	WebElement BUTTON_SIGNIN = driver.findElement(btnSignin);
	BUTTON_SIGNIN.click();
	System.out.println("Login is in progress...");
	return new VoloTeaUserProfile(driver);
    }

}
