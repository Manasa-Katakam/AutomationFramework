package com.atm.modulefive.webdriver.advanced.tests;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.atm.modulefive.webdriver.advanced.configuration.ChromeDriverCreator;
import com.atm.modulefive.webdriver.advanced.configuration.CustomListener;
import com.atm.modulefive.webdriver.advanced.configuration.Decorator;
import com.atm.modulefive.webdriver.advanced.configuration.WebDriverCreator;
import com.atm.modulefive.webdriver.advanced.pageobjects.VoloTeaFlightSearch;
import com.atm.modulefive.webdriver.advanced.testdata.DataUtility;


public class VoloTeaTest {

	private WebDriverCreator creator;
	private WebDriver driver;
	
	Logger logger = LogManager.getRootLogger();

	@Test(description = "Start Browser, maximize and add implicit sync wait time")
	public void startBrowser() {
		logger.info("Launching new browser instance and navigating to VoloTea Application");
		creator = new ChromeDriverCreator();
		driver = creator.factoryMethod();
		driver.get(DataUtility.getStartUrl());
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		driver.manage().window().maximize();
	}

	@Test(dependsOnMethods = "startBrowser", description = "Search Flights from Prague to Venice")
	public void EnterOriginReturnDetails() throws InterruptedException {
		WebDriver decoratedDriver = new Decorator(driver);
		new VoloTeaFlightSearch(decoratedDriver).addOriginReturnDetails();
		logger.info("Entered the Origin and Return Locations with specific dates");
		Assert.assertTrue(new VoloTeaFlightSearch(decoratedDriver).flightSearchCorrect(),
				"Flight details are incorrect to proceed");
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		logger.info("Closing the driver instance");
		WebDriver decoratedDriver = new Decorator(driver);
		decoratedDriver.quit();

	}
}
