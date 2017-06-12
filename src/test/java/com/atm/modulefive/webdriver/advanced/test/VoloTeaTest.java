package com.atm.modulefive.webdriver.advanced.test;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atm.modulefive.webdriver.advanced.utils.DataUtility;
import com.atm.modulefive.webdriver.advanced.driver.DefaultDriver;
import com.atm.modulefive.webdriver.advanced.pages.VoloTeaFlightSearch;
import com.atm.modulefive.webdriver.advanced.pages.VoloTeaFlightSummary;
import com.atm.modulefive.webdriver.advanced.pages.VoloTeaSignIn;
import com.atm.modulefive.webdriver.advanced.pages.VoloTeaUserProfile;

public class VoloTeaTest {
	
	private static final String PASSENGER_COUNT = "2";
	private static final String BROWSER_TYPE = "firefox";
	private WebDriver driver;	

	@BeforeClass(description = "Start Browser, maximize and add implicit sync wait time")
	public void startBrowser() {
		driver=DefaultDriver.initializeDriver(BROWSER_TYPE);
		driver.get(DataUtility.getStartUrl());
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		driver.manage().window().maximize();
	}

	@Test(description = "Search Flights from Prague to Venice")
	public void EnterOriginReturnDetails() throws InterruptedException {
		new VoloTeaFlightSearch(driver).addOriginReturnDetails();
		System.out.println("Entered the Origin and Return Locations with specific dates");
		// [IK] Add assertion here. Each @Test should contain assertion. 
	}

	@Test(dependsOnMethods = "EnterOriginReturnDetails", description = "Search Flights with given details")
	public void SearchFlights() throws InterruptedException {
		new VoloTeaFlightSearch(driver).doFlightSearch(DataUtility.getChildrenCount());
		Assert.assertTrue(new VoloTeaFlightSummary(driver).getPassengerCount().contains(PASSENGER_COUNT),
				"Flights Search query made with incorrect passebger count");
		System.out.println("Completed the Flight Search with specific Details"); // [IK] Don't write code after assertions. It will not be executed in case the assertion fails.
	}

	@Test(dependsOnMethods = "SearchFlights", description = "Validate the Search query made previously")
	public void FlightInformation() {
		Assert.assertTrue(new VoloTeaFlightSummary(driver).isFlightDisplayed(),
				"Flight Details are not displayed for the Search made!");
		System.out.println("*****Outbound and Return Flight Details*****"); // [IK] Don't write code after assertions. It will not be executed in case the assertion fails.
		System.out.println(new VoloTeaFlightSummary(driver).getOriginFlightDetails());
		System.out.println(new VoloTeaFlightSummary(driver).getReturnFlightDetails());
		System.out.println(
				"Flight Search with given details have been made and the list of available flights are visible");
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		driver.quit();
	}
}
