package com.atm.modulefive.webdriver.advanced.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.atm.modulefive.webdriver.advanced.configuration.Decorator;
import com.atm.modulefive.webdriver.advanced.configuration.DefaultDriver;
import com.atm.modulefive.webdriver.advanced.pageobjects.VoloTeaFlightSearch;
import com.atm.modulefive.webdriver.advanced.pageobjects.VoloTeaFlightSummary;
import com.atm.modulefive.webdriver.advanced.testdata.DataUtility;

public class VoloTeaTest2{
	private static final String PASSENGER_COUNT = "2";
	private WebDriver driver;
	
	/**
	 * [MK] Implemented Singleton Pattern
	 * @throws InterruptedException
	 */
	@Test(description = "Search Flights with given details")
	public void SearchFlights() throws InterruptedException {
		driver = DefaultDriver.initializeDriver(); // Singleton Implementation
		driver.get(DataUtility.getStartUrl());
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		driver.manage().window().maximize();
		new VoloTeaFlightSearch(driver).addOriginReturnDetails();
		System.out.println("Initiate the Flight Search with specific Details suppied...");
		new VoloTeaFlightSearch(driver).doFlightSearch(DataUtility.getChildrenCount());
		Assert.assertTrue(new VoloTeaFlightSummary(driver).getPassengerCount().contains(PASSENGER_COUNT),
				"Flights Search query made with incorrect passebger count"); 
		// [IK] Don't put the code after assertions, because if the assertion fails, this code will not be executed.
		// [MK] Updated the same with a new logger as needed at that step
	}
	
	/**
	 * [MK] Implemented Decorator Pattern
	 * @throws InterruptedException
	 */
	@Test(dependsOnMethods = "SearchFlights", description = "Validate the Search query made previously")
	public void FlightInformation() throws InterruptedException {
		// [IK] Don't put the code after assertions, because if the assertion
		// fails, this code will not be executed.
		// [MK] Updated the same with a new logger as needed at that step
		WebDriver decoratedDriver = new Decorator(driver); // Decorator Implementation
		System.out.println("*****Outbound and Return Flight Details*****");
		System.out.println(new VoloTeaFlightSummary(decoratedDriver).getOriginFlightDetails());
		System.out.println(new VoloTeaFlightSummary(decoratedDriver).getReturnFlightDetails());
		System.out.println(
				"Flight Search with given details have been made and the list of available flights are visible");
		Assert.assertTrue(new VoloTeaFlightSummary(decoratedDriver).isFlightDisplayed(),
				"Flight Details are not displayed for the Search made!");
	}
	
	/**
	 * [MK] Implemented Singleton Pattern
	 */
	
	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		DefaultDriver.closeBrowser();
	}

}
