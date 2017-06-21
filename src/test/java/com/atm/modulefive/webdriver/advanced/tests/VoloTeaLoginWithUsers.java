package com.atm.modulefive.webdriver.advanced.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atm.modulefive.webdriver.advanced.configuration.DefaultDriver;
import com.atm.modulefive.webdriver.advanced.pageobjects.VoloTeaSignIn;
import com.atm.modulefive.webdriver.advanced.testdata.DataUtility;
import com.atm.modulefive.webdriver.advanced.testdata.User;

public class VoloTeaLoginWithUsers {

	WebDriver driver = DefaultDriver.initializeDriver();

    /*
     * [IK] Move to singleton the settings for WebDriver. 
     * [MK] Moved the browser's driver intialization to Singleton class
     */
    @BeforeClass(description = "Start Browser, maximize and add implicit sync wait time")
    public void startBrowser() {
	driver.get(DataUtility.getStartUrl());
	driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
	driver.manage().window().maximize();
    }

    @Test(description = "SignIn to VoloTea Application")
    public void LoginToVoloTea() throws InterruptedException {
	System.out.println("USER login initiated...");
	boolean signInComplete = new VoloTeaSignIn(driver).doLogin(new User()).loginIsCorrect();
	Assert.assertTrue(signInComplete, "User Authentication Failed, Not Logged in!");

    }
}
