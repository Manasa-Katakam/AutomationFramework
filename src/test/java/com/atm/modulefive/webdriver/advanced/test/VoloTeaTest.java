package com.atm.modulefive.webdriver.advanced.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.atm.modulefive.webdriver.advanced.driver.ChromeDriverCreator;
import com.atm.modulefive.webdriver.advanced.driver.Decorator;
import com.atm.modulefive.webdriver.advanced.driver.WebDriverCreator;
import com.atm.modulefive.webdriver.advanced.pages.VoloTeaFlightSearch;
import com.atm.modulefive.webdriver.advanced.utils.DataUtility;

public class VoloTeaTest {

    private WebDriverCreator creator;
    private WebDriver driver;

    /**
     * [MK] Implemented Factory Method pattern
     */
    @Test(description = "Start Browser, maximize and add implicit sync wait time")
    public void startBrowser() {
	creator = new ChromeDriverCreator();
	driver = creator.factoryMethod();
	driver.get(DataUtility.getStartUrl());
	driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
	driver.manage().window().maximize();
    }

    /**
     * [MK] Implemented Decorator Pattern
     * 
     * @throws InterruptedException
     */

    @Test(dependsOnMethods = "startBrowser", description = "Search Flights from Prague to Venice")
    public void EnterOriginReturnDetails() throws InterruptedException {
	WebDriver decoratedDriver = new Decorator(driver);
	new VoloTeaFlightSearch(decoratedDriver).addOriginReturnDetails();
	System.out.println("Entered the Origin and Return Locations with specific dates");
	Assert.assertTrue(new VoloTeaFlightSearch(decoratedDriver).flightSearchCorrect(),
		"Flight details are incorrect to proceed");
	// [IK] Each @Test should contain assertion. There's no assertion here.
	// [MK] Added a new assertion to validate the next step related element
	// is displayed
    }

    /**
     * [MK] Implemented Decorator Pattern
     * 
     * @throws InterruptedException
     */
    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
	WebDriver decoratedDriver = new Decorator(driver);
	decoratedDriver.quit();

    }
}
