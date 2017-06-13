package com.atm.modulefive.webdriver.advanced.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.atm.modulefive.webdriver.advanced.utils.ActionUtility;

public class VoloTeaUserProfile extends VoloTeaAbstract {

	private final By profile = By.xpath("//div[@id='userNavbarOptions']");

	public VoloTeaUserProfile(WebDriver driver) {
		super(driver);
	}

	public boolean loginIsCorrect() throws InterruptedException {
		ActionUtility.waitForPageLoaded(driver);
		WebElement LINK_YOUR_PROFILE = driver.findElement(profile);
		return LINK_YOUR_PROFILE.isDisplayed();
	}
}
