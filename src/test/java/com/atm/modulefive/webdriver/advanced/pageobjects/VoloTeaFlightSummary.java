package com.atm.modulefive.webdriver.advanced.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VoloTeaFlightSummary extends VoloTeaAbstract {

    public VoloTeaFlightSummary(WebDriver driver) {
	super(driver);
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
