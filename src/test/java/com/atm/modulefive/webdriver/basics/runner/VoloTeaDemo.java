package com.atm.modulefive.webdriver.basics.runner;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atm.modulefive.webdriver.basics.testdata.Constants;
import com.atm.modulefive.webdriver.basics.utils.ActionUtility;

public class VoloTeaDemo {



	private static final String LINK_YOUR_PROFILE = "userNavbarOptions"; // name

	private static final String LABEL_PASSENGER_COUNT = "//div[@class='resume-wrapper']//p";
	
	private static final String LABEL_ORIGIN = "//td[contains(text(),'Outbound')]/..//strong/..";

	private static final String LABEL_RETURN = "//td[contains(text(),'Return')]/..//strong/..";
	
	private static final String PASSENGER_COUNT = "2";

	private static WebDriver driver;

	@BeforeClass(description = "Start Browser, maximize and add implicit sync wait time")
	public void startBrowser() {
		System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		driver = new ChromeDriver(capabilities);
		driver.get(Constants.getStartUrl());
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		driver.manage().window().maximize();
	}

	@Test(description = "SignIn to VoloTea Application")
	public void VoloTeaSignIn() {
		VoloTeaImplementation.doLogin(driver,Constants.getEmail(), Constants.getPassword());
		System.out.println("Logged in succesfully to with the authenticated USER"); 
		VoloTeaImplementation.verifyTitle(driver, Constants.getPagetitle()); // By Regular Expression
		Assert.assertTrue(ActionUtility.isElementPresent(driver, By.id(LINK_YOUR_PROFILE)),
				"User Authentiction Failed and Login Unsuccessful"); 
		
	}

	@Test(dependsOnMethods = "VoloTeaSignIn", description = "Search Flights from Prague to Venice")
	public void SearchFlights() throws InterruptedException {
		VoloTeaImplementation.addOriginReturnLocation(driver);
		
		Constants.getInstance().setStartdate(VoloTeaImplementation.addRandomStartDate(driver)); 
		Constants.getInstance().setReturndate(VoloTeaImplementation.addRandomReturnDate(driver));
		
		VoloTeaImplementation.doFlightSearch(driver);
		Assert.assertTrue(ActionUtility.isElementPresent(driver, By.xpath(LABEL_PASSENGER_COUNT)),
				"The Passenger count hasn't been updated with the Sarch Criteria made"); 
		System.out.println("Completed the Flight Search with specific Details");
	}

	@Test(dependsOnMethods = "SearchFlights", description = "Validate the Search query made previously")
	public void FlightInformation() {
		// Verifying Flight Details
		Assert.assertTrue(ActionUtility.getElementValue(driver, By.xpath(LABEL_ORIGIN)).contains(Constants.getInstance().getStartdate()), // [IK] Put each assertion to the single @Test method. One @Test should contain one assertion.
				"In search results page, the origin date selected is : " + Constants.getInstance().getStartdate());																																			//[MK] Done
		Assert.assertTrue(ActionUtility.getElementValue(driver, By.xpath(LABEL_RETURN)).contains(Constants.getInstance().getReturndate()), // [IK] Put each assertion to the single @Test method. One @Test should contain one assertion.
				"In search results page, the return date selected is : " + Constants.getInstance().getReturndate());
		// Verifying the Passenger Count as 2
		Assert.assertTrue(ActionUtility.getElementValue(driver, By.xpath(LABEL_PASSENGER_COUNT)).contains(PASSENGER_COUNT), // [IK] Put each assertion to the single @Test method. One @Test should contain one assertion. 
				"The Total number of Passengers doesn't match with the expected, i.e "+PASSENGER_COUNT); 		
		VoloTeaImplementation.validateSearchResult(driver);
		System.out.println("Flight Search with given details have been made and the list of available flights are visible");
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		driver.quit();
	}

}
