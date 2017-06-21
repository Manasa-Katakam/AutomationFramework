package com.atm.modulefive.webdriver.advanced.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atm.modulefive.webdriver.advanced.configuration.Decorator;
import com.atm.modulefive.webdriver.advanced.configuration.DefaultDriver;
import com.atm.modulefive.webdriver.advanced.pageobjects.VoloTeaFlightSearch;
import com.atm.modulefive.webdriver.advanced.pageobjects.VoloTeaFlightSummary;
import com.atm.modulefive.webdriver.advanced.testdata.DataUtility;

public class VoloTeaTestwithSingleton {
    private static final String PASSENGER_COUNT = "2";
    protected WebDriver driver; // [IK] Use singleton here. The code is commented below.
//  WebDriver driver = DefaultDriver.initializeDriver();

    /*
     * [IK] Move to singleton the settings for WebDriver. 
     * 
     */
    @BeforeClass(description = "Intializw webdriver and launch application")
    public void startBrowser() {
	driver = DefaultDriver.initializeDriver(); // Singleton Implementation // [IK] A duplication of Singleton.
	driver.get(DataUtility.getStartUrl());
	driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
	driver.manage().window().maximize();
    }

    @Test(description = "Search Flights with given details")
    public void SearchFlights() throws InterruptedException {
	new VoloTeaFlightSearch(driver).addOriginReturnDetails();
	System.out.println("Initiate the Flight Search with specific Details suppied...");
	new VoloTeaFlightSearch(driver).doFlightSearch(DataUtility.getChildrenCount());
	Assert.assertTrue(new VoloTeaFlightSummary(driver).getPassengerCount().contains(PASSENGER_COUNT),
		"Flights Search query made with incorrect passebger count");

    }

    @Test(dependsOnMethods = "SearchFlights", description = "Validate the Search query made previously")
    public void FlightInformation() throws InterruptedException {
	WebDriver decoratedDriver = new Decorator(driver); // Decorator // [IK] let's use singleton for the whole project.
							   // Implementation
	System.out.println("*****Outbound and Return Flight Details*****");
	System.out.println(new VoloTeaFlightSummary(decoratedDriver).getOriginFlightDetails());
	System.out.println(new VoloTeaFlightSummary(decoratedDriver).getReturnFlightDetails());
	System.out.println(
		"Flight Search with given details have been made and the list of available flights are visible");
	Assert.assertTrue(new VoloTeaFlightSummary(decoratedDriver).isFlightDisplayed(),
		"Flight Details are not displayed for the Search made!");
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
	DefaultDriver.closeBrowser();
    }

}
