package com.atm.modulefive.webdriver.advanced.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.atm.modulefive.webdriver.advanced.configuration.DefaultDriver;
import com.atm.modulefive.webdriver.advanced.utils.ActionUtility;

public class VoloTeaFlightSummary {

	// [IK] Better use singleton [MK] Done
	WebDriver driver = DefaultDriver.initializeDriver();
	public VoloTeaFlightSummary(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='resume-wrapper']//p")
	private WebElement LABEL_PASSENGER_COUNT;

	@FindBy(xpath = "//td[contains(text(),'Outbound')]/..//strong/..")
	private WebElement LABEL_ORIGIN;

	@FindBy(xpath = "//td[contains(text(),'Return')]/..//strong/..")
	private WebElement LABEL_RETURN;

	public String getPassengerCount() {
		String passengerCount = LABEL_PASSENGER_COUNT.getText();
		return passengerCount;
	}

	public boolean isFlightDisplayed() {
		ActionUtility.waitForPageLoaded(driver);
		boolean flight = LABEL_ORIGIN.isDisplayed();
		return flight;
	}

	public String getOriginFlightDetails() {
		String originFlight = LABEL_ORIGIN.getText();
		return originFlight;
	}

	public String getReturnFlightDetails() {
		String returnFlight = LABEL_RETURN.getText();
		return returnFlight;
	}

}
