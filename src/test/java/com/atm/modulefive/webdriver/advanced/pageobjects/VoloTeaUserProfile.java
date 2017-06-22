package com.atm.modulefive.webdriver.advanced.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.atm.modulefive.webdriver.advanced.configuration.DefaultDriver;
import com.atm.modulefive.webdriver.advanced.utils.ActionUtility;

public class VoloTeaUserProfile {

	// [IK] Better use singleton [MK] Done
	WebDriver driver = DefaultDriver.initializeDriver();
	
	public VoloTeaUserProfile(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userNavbarOptions")
	private WebElement LINK_YOUR_PROFILE;

	public boolean loginIsCorrect() {
		ActionUtility.waitForPageLoaded(driver);
		return LINK_YOUR_PROFILE.isDisplayed();

	}
}
