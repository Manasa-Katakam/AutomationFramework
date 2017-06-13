package com.atm.modulefive.webdriver.advanced.test;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atm.modulefive.webdriver.advanced.utils.DataUtility;
import com.atm.modulefive.webdriver.advanced.pages.VoloTeaFlightSearch;
import com.atm.modulefive.webdriver.advanced.pages.VoloTeaFlightSummary;
import com.atm.modulefive.webdriver.advanced.pages.VoloTeaSignIn;
import com.atm.modulefive.webdriver.advanced.pages.VoloTeaUserProfile;

public class VoloTeaTest {
	
	private static final String PASSENGER_COUNT = "2";
	private static WebDriver driver;

	@BeforeClass(description = "Start Browser, maximize and add implicit sync wait time")
	public void startBrowser() {
		System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		driver = new ChromeDriver(capabilities);
		driver.get(DataUtility.getStartUrl());
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		driver.manage().window().maximize();
	}

	@Test(description = "SignIn to VoloTea Application")
	public void LoginToVoloTea() throws InterruptedException {
		System.out.println("USER login initiated..."); 
		new VoloTeaSignIn(driver).doLogin(DataUtility.getEmail(), DataUtility.getPassword());
		Assert.assertTrue(new VoloTeaUserProfile(driver).loginIsCorrect(),
				"User Authentication Failed, Not Logged in!");
		// [IK] Don't put the code after assertions, because if the assertion fails, this code will not be executed.
		// [MK] Updated the same with a new logger as needed at that step
	}

	@Test(dependsOnMethods = "LoginToVoloTea", description = "Search Flights from Prague to Venice")
	public void EnterOriginReturnDetails() throws InterruptedException {
		new VoloTeaFlightSearch(driver).addOriginReturnDetails();
		System.out.println("Entered the Origin and Return Locations with specific dates");
		Assert.assertTrue(new VoloTeaFlightSearch(driver).flightSearchCorrect(),
				"Flight details are incorrect to proceed");
		// [IK] Each @Test should contain assertion. There's no assertion here.
		// [MK] Added a new assertion to validate the next step related element is displayed
	}

	@Test(dependsOnMethods = "EnterOriginReturnDetails", description = "Search Flights with given details")
	public void SearchFlights() {
		System.out.println("Initiate the Flight Search with specific Details suppied...");
		new VoloTeaFlightSearch(driver).doFlightSearch(DataUtility.getChildrenCount());
		Assert.assertTrue(new VoloTeaFlightSummary(driver).getPassengerCount().contains(PASSENGER_COUNT),
				"Flights Search query made with incorrect passebger count"); 
		// [IK] Don't put the code after assertions, because if the assertion fails, this code will not be executed.
		// [MK] Updated the same with a new logger as needed at that step
	}

	@Test(dependsOnMethods = "SearchFlights", description = "Validate the Search query made previously")
	public void FlightInformation() {
		// [IK] Don't put the code after assertions, because if the assertion fails, this code will not be executed.
		// [MK] Updated the same with a new logger as needed at that step
		System.out.println("*****Outbound and Return Flight Details*****"); 
		System.out.println(new VoloTeaFlightSummary(driver).getOriginFlightDetails());
		System.out.println(new VoloTeaFlightSummary(driver).getReturnFlightDetails());
		System.out.println(
				"Flight Search with given details have been made and the list of available flights are visible");
		Assert.assertTrue(new VoloTeaFlightSummary(driver).isFlightDisplayed(),
				"Flight Details are not displayed for the Search made!");		
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		driver.quit();
	}
}
