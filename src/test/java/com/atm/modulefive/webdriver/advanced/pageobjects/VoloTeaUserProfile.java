package com.atm.modulefive.webdriver.advanced.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.atm.modulefive.webdriver.advanced.utils.ActionUtility;

public class VoloTeaUserProfile extends VoloTeaAbstract {

    public VoloTeaUserProfile(WebDriver driver) {
	super(driver);
    }

    @FindBy(id = "userNavbarOptions")
    private WebElement LINK_YOUR_PROFILE;

    public boolean loginIsCorrect() {
	ActionUtility.waitForPageLoaded(driver);
	return LINK_YOUR_PROFILE.isDisplayed();

    }
}
