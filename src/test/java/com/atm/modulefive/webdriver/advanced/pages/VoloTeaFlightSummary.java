package com.atm.modulefive.webdriver.advanced.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VoloTeaFlightSummary extends VoloTeaAbstract {

    private final By panCount = By.xpath("//div[@class='resume-wrapper']//p");
    private final By originDetail = By.xpath("//td[contains(text(),'Outbound')]/..//strong/..");
    private final By returnDetail = By.xpath("//td[contains(text(),'Return')]/..//strong/..");

    public VoloTeaFlightSummary(WebDriver driver) {
	super(driver);
    }

    public String getPassengerCount() {
	WebElement LABEL_PASSENGER_COUNT = driver.findElement(panCount);
	String passengerCount = LABEL_PASSENGER_COUNT.getText();
	return passengerCount;
    }

    public boolean isFlightDisplayed() {
	WebElement LABEL_ORIGIN = driver.findElement(originDetail);
	boolean flight = LABEL_ORIGIN.isDisplayed();
	return flight;
    }

    public String getOriginFlightDetails() {
	WebElement LABEL_ORIGIN = driver.findElement(originDetail);
	String originFlight = LABEL_ORIGIN.getText();
	return originFlight;
    }

    public String getReturnFlightDetails() {
	WebElement LABEL_RETURN = driver.findElement(returnDetail);
	String returnFlight = LABEL_RETURN.getText();
	return returnFlight;
    }

}
