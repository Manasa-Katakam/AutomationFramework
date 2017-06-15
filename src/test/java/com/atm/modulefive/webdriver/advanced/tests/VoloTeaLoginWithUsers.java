package com.atm.modulefive.webdriver.advanced.tests;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atm.modulefive.webdriver.advanced.configuration.Decorator;
import com.atm.modulefive.webdriver.advanced.pageobjects.VoloTeaSignIn;
import com.atm.modulefive.webdriver.advanced.testdata.DataUtility;
import com.atm.modulefive.webdriver.advanced.testdata.User;

public class VoloTeaLoginWithUsers {
	
	private static WebDriver driver;
	Logger logger = LogManager.getRootLogger();
	
	@BeforeClass(description = "Start Browser, maximize and add implicit sync wait time")
	public void startBrowser() {
		logger.info("Launching new browser instance and navigating to VoloTea Application");
		System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		driver = new ChromeDriver(capabilities);
		driver.get(DataUtility.getStartUrl());
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		driver.manage().window().maximize();
	}

	@Test(description = "SignIn to VoloTea Application")
	public void LoginToVoloTea() throws InterruptedException {
		logger.info("USER login initiated...");
		boolean signInComplete = new VoloTeaSignIn(driver).doLogin(new User())
				.loginIsCorrect();
		Assert.assertTrue(signInComplete, "User Authentication Failed, Not Logged in!");

	}
	
	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		logger.info("Closing the driver instance");
		WebDriver decoratedDriver = new Decorator(driver);
		decoratedDriver.quit();

	}
}
