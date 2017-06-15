package com.atm.modulefive.webdriver.advanced.tests;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.atm.modulefive.webdriver.advanced.configuration.CustomListener;
import com.atm.modulefive.webdriver.advanced.configuration.Decorator;
import com.atm.modulefive.webdriver.advanced.configuration.DefaultDriver;
import com.atm.modulefive.webdriver.advanced.pageobjects.VoloTeaFlightSearch;
import com.atm.modulefive.webdriver.advanced.pageobjects.VoloTeaFlightSummary;
import com.atm.modulefive.webdriver.advanced.testdata.DataUtility;

@Listeners(CustomListener.class)
public class VoloTeaTest2 {
	private static final String PASSENGER_COUNT = "2";
	private WebDriver driver;
	Logger logger = LogManager.getRootLogger();

	@Test(description = "Search Flights with given details")
	public void SearchFlights() throws InterruptedException {
		logger.info("Launching new browser and searching for Flights on VoloTea");
		driver = DefaultDriver.initializeDriver(); // Singleton Implementation
		driver.get(DataUtility.getStartUrl());
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		driver.manage().window().maximize();
		new VoloTeaFlightSearch(driver).addOriginReturnDetails();
		logger.info("Initiate the Flight Search with specific Details suppied...");
		new VoloTeaFlightSearch(driver).doFlightSearch(DataUtility.getChildrenCount());
		Assert.assertTrue(new VoloTeaFlightSummary(driver).getPassengerCount().contains(PASSENGER_COUNT),
				"Flights Search query made with incorrect passebger count");
	}

	@Test(dependsOnMethods = "SearchFlights", description = "Validate the Search query made previously")
	public void FlightInformation() throws InterruptedException {

		WebDriver decoratedDriver = new Decorator(driver); // Decorator
															// Implementation
		logger.info("*****Outbound and Return Flight Details*****");
		logger.info(new VoloTeaFlightSummary(decoratedDriver).getOriginFlightDetails());
		logger.info(new VoloTeaFlightSummary(decoratedDriver).getReturnFlightDetails());
		logger.info("Flight Search with given details have been made and the list of available flights are visible");
		Assert.assertTrue(new VoloTeaFlightSummary(decoratedDriver).isFlightDisplayed(),
				"Flight Details are not displayed for the Search made!");
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		logger.info("Closing the driver instance");
		DefaultDriver.closeBrowser();
	}

}
