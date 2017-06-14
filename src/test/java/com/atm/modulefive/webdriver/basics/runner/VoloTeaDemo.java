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
	VoloTeaImplementation.doLogin(driver, Constants.getEmail(), Constants.getPassword());
	System.out.println("Logged in succesfully to with the authenticated USER");
	Assert.assertTrue(ActionUtility.isElementPresent(driver, By.id(LINK_YOUR_PROFILE)),
		"User Authentiction Failed and Login Unsuccessful");
    }

    @Test(dependsOnMethods = "VoloTeaSignIn", description = "Verify page title")
    public void verifyTitle() {
	String title = driver.getTitle();
	Assert.assertTrue(title.matches("VOLO[A-Z]...*"), "Title doesn't match with the Regular Expression");
    }

    @Test(dependsOnMethods = "VoloTeaSignIn", description = "Search Flights from Prague to Venice")
    public void SearchFlights() throws InterruptedException {
	System.out.println("Entering the Flight Details to search...");
	VoloTeaImplementation.addOriginReturnLocation(driver);
	Constants.getInstance().setStartdate(VoloTeaImplementation.addRandomStartDate(driver));
	Constants.getInstance().setReturndate(VoloTeaImplementation.addRandomReturnDate(driver));
	VoloTeaImplementation.doFlightSearch(driver);
	Assert.assertTrue(ActionUtility.isElementPresent(driver, By.xpath(LABEL_PASSENGER_COUNT)),
		"The Passenger count hasn't been updated with the Sarch Criteria made");

    }

    @Test(dependsOnMethods = "SearchFlights", description = "Validate Origin Details")
    public void FlightOriginInformation() {
	Assert.assertTrue(
		ActionUtility.getElementValue(driver, By.xpath(LABEL_ORIGIN))
			.contains(Constants.getInstance().getStartdate()),
		"In search results page, the origin date selected is : " + Constants.getInstance().getStartdate());
    }

    @Test(dependsOnMethods = "SearchFlights", description = "Validate Return Details")
    public void FlightReturnInformation() {
	Assert.assertTrue(
		ActionUtility.getElementValue(driver, By.xpath(LABEL_RETURN))
			.contains(Constants.getInstance().getReturndate()),
		"In search results page, the return date selected is : " + Constants.getInstance().getReturndate());
    }

    @Test(dependsOnMethods = "SearchFlights", description = "Validate Flight Search and Passenger Details")
    public void FlightInformation() {
	VoloTeaImplementation.validateSearchResult(driver);
	System.out.println(
		"Flight Search with given details have been made and the list of available flights are visible");
	Assert.assertTrue(
		ActionUtility.getElementValue(driver, By.xpath(LABEL_PASSENGER_COUNT)).contains(PASSENGER_COUNT),
		"The Total number of Passengers doesn't match with the expected, i.e " + PASSENGER_COUNT);

    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
	driver.quit();
    }

}
