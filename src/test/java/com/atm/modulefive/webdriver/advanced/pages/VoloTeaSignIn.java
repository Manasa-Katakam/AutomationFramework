package com.atm.modulefive.webdriver.advanced.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.atm.modulefive.webdriver.advanced.utils.ActionUtility;

public class VoloTeaSignIn extends VoloTeaAbstract {
	
	@FindBy(css = "a.switcherLogin")
	private WebElement LINK_SIGNIN;

	@FindBy(id = "emailLoginForm")
	private WebElement INPUT_EMAIL;

	@FindBy(id = "passwordLoginForm")
	private WebElement INPUT_PASSWORD;

	@FindBy(xpath = "//form[@id='loginForm']//a[contains(@class,'voloteaButton')]")
	private WebElement BUTTON_SIGNIN;
	
	public VoloTeaSignIn(WebDriver driver) {
		super(driver);		
	}

	public VoloTeaUserProfile doLogin(String email, String password) throws InterruptedException {
		ActionUtility.waitForPageLoaded(driver);
		ActionUtility.waitForElementClickable(driver, 6, LINK_SIGNIN);
		ActionUtility.jsClick(driver, LINK_SIGNIN);
		ActionUtility.waitForPageLoaded(driver);
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
