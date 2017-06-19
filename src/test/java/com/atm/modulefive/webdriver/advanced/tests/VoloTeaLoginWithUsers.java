package com.atm.modulefive.webdriver.advanced.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atm.modulefive.webdriver.advanced.pageobjects.VoloTeaSignIn;
import com.atm.modulefive.webdriver.advanced.testdata.DataUtility;
import com.atm.modulefive.webdriver.advanced.testdata.User;

public class VoloTeaLoginWithUsers {

    protected WebDriver driver; // [IK] Use singleton here. The code is commented below.
//  WebDriver driver = DefaultDriver.initializeDriver();

    /*
     * [IK] Move to singleton the settings for WebDriver. 
     * 
     */
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
	boolean signInComplete = new VoloTeaSignIn(driver).doLogin(new User()).loginIsCorrect();
	Assert.assertTrue(signInComplete, "User Authentication Failed, Not Logged in!");

    }
}
