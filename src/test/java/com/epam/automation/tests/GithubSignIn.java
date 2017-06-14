package com.epam.automation.tests;

import com.epam.automation.model.Constants;
import com.epam.automation.model.CustomException;
import com.epam.automation.pages.HomePage;
import com.epam.automation.pages.SignInPage;
import com.epam.automation.pages.StartPage;

import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.WebDriver;

public class GithubSignIn {

    WebDriver driver = WebDriverInitialize.getWebDriverInstance();

    @Test
    public void testOneCanLoginGithub() throws CustomException {
	StartPage startPage = new StartPage(driver);
	startPage.openGitHubURL();
	SignInPage signInPage = startPage.invokeSignIn();
	HomePage homePage = signInPage.signInGitHub(Constants.getUsername(), Constants.getPassword());
	String loggedInUserName = homePage.getLoggedInUserName();
	Assert.assertEquals(Constants.getUsername(), loggedInUserName);
    }
}
