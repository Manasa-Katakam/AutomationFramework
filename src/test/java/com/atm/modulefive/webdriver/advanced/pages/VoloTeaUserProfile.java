package com.atm.modulefive.webdriver.advanced.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.atm.modulefive.webdriver.advanced.utils.ActionUtility;


public class VoloTeaUserProfile extends VoloTeaAbstract{

	@FindBy(id = "userNavbarOptions")
	private WebElement LINK_YOUR_PROFILE;

	public VoloTeaUserProfile(WebDriver driver) {
		super(driver);
	}

	public boolean loginIsCorrect() {
		ActionUtility.waitForPageLoaded(driver);
		return LINK_YOUR_PROFILE.isDisplayed();

	}
}

