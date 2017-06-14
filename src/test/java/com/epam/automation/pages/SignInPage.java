package com.epam.automation.pages;

import com.epam.automation.model.CustomException;
import com.epam.automation.pages.HomePage;
import com.epam.automation.tests.WebDriverInitialize;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

    @FindBy(id = "login_field")
    private WebElement textboxUsername;

    @FindBy(id = "password")
    private WebElement textboxPassword;

    @FindBy(xpath = "//input[@value='Sign in']")
    private WebElement buttonSignIn;

    WebDriver driver = WebDriverInitialize.getWebDriverInstance();

    public SignInPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }

    public HomePage signInGitHub(String username, String password) throws CustomException {
	try {
	    textboxUsername.sendKeys(username);
	    textboxPassword.sendKeys(password);
	    buttonSignIn.click();
	} catch (StaleElementReferenceException e) {
	    System.out.println("Element value has been reloaded or expired " + e);
	} catch (TimeoutException e) {
	    System.out.println("Appliaction not loaded completely" + e);
	} catch (NoSuchElementException e) {
	    System.out.println("Element not found " + e);
	}

	return new HomePage(driver);
    }

    public boolean isReadyToSignIn() throws ElementNotVisibleException {
	if (buttonSignIn.isDisplayed()) {
	    return true;
	} else {
	    throw new ElementNotVisibleException("The Button SignIn is not Visible On the UI");
	}
    }

}
