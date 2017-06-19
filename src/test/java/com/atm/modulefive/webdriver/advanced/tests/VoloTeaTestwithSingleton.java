package com.atm.modulefive.webdriver.advanced.tests;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.atm.modulefive.webdriver.advanced.configuration.CustomListener;
import com.atm.modulefive.webdriver.advanced.configuration.Decorator;
import com.atm.modulefive.webdriver.advanced.configuration.DefaultDriver;
import com.atm.modulefive.webdriver.advanced.pageobjects.VoloTeaFlightSearch;
import com.atm.modulefive.webdriver.advanced.pageobjects.VoloTeaFlightSummary;
import com.atm.modulefive.webdriver.advanced.pageobjects.VoloTeaSignIn;
import com.atm.modulefive.webdriver.advanced.testdata.DataUtility;
import com.atm.modulefive.webdriver.advanced.testdata.User;

@Listeners(CustomListener.class)
public class VoloTeaTestwithSingleton {
	private static final String PASSENGER_COUNT = "2";
	private WebDriver driver;
	Logger logger = LogManager.getRootLogger();

	CustomListener listener = new CustomListener();

	@BeforeClass(description = "Intialize webdriver and launch application")
	public void startBrowser() {
		driver = DefaultDriver.initializeDriver(); // Singleton Implementation
		driver.get(DataUtility.getStartUrl());
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		driver.manage().window().maximize();
		listener.takeIntermediateScreenshot("Test 4"); // [IK] Added to check
														// screenshots
	}

	@Test(description = "SignIn to VoloTea Application")
	public void LoginToVoloTea() throws InterruptedException {
		logger.info("USER login initiated...");
		boolean signInComplete = new VoloTeaSignIn(driver).doLogin(new User()).loginIsCorrect();
		listener.takeIntermediateScreenshot("Test 5"); // [IK] Added to check
														// screenshots
		Assert.assertTrue(signInComplete, "User Authentication Failed, Not Logged in!");
	}

	@Test(description = "Search Flights with given details", dependsOnMethods = "LoginToVoloTea")
	public void SearchFlights() throws InterruptedException {
		new VoloTeaFlightSearch(driver).addOriginReturnDetails();
		System.out.println("Initiate the Flight Search with specific Details suppied...");
		new VoloTeaFlightSearch(driver).doFlightSearch(DataUtility.getChildrenCount());
		listener.takeIntermediateScreenshot("Test 6"); // [IK] Added to check
														// screenshots
		Assert.assertTrue(new VoloTeaFlightSummary(driver).getPassengerCount().contains(PASSENGER_COUNT),
				"Flights Search query made with incorrect passebger count");
	}

	@Test(dependsOnMethods = "SearchFlights", description = "Validate the Search query made previously")
	public void FlightInformation() throws InterruptedException {
		WebDriver decoratedDriver = new Decorator(driver); // Decorator
															// Implementation
		System.out.println("*****Outbound and Return Flight Details*****");
		System.out.println(new VoloTeaFlightSummary(decoratedDriver).getOriginFlightDetails());
		System.out.println(new VoloTeaFlightSummary(decoratedDriver).getReturnFlightDetails());
		System.out.println(
				"Flight Search with given details have been made and the list of available flights are visible");
		listener.takeIntermediateScreenshot("Test 7"); // [IK] Added to check
														// screenshots
		Assert.assertTrue(new VoloTeaFlightSummary(decoratedDriver).isFlightDisplayed(),
				"Flight Details are not displayed for the Search made!");
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		DefaultDriver.closeBrowser();
	}

}
