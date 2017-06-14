package com.atm.modulefive.webdriver.basics.runner;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.atm.modulefive.webdriver.basics.utils.ActionUtility;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class VoloTeaImplementation {
    private static final String LINK_SIGNIN = "switcherLogin"; // Classname

    private static final String INPUT_EMAIL = "emailLoginForm"; // id

    private static final String INPUT_PASSWORD = "passwordLoginForm";

    private static final String BUTTON_SIGNIN = "//form[@id='loginForm']//a[contains(@class,'voloteaButton')]";

    private static final String LINK_YOUR_PROFILE = "userNavbarOptions"; // name

    private static final String FIELD_ORIGIN = "//input[@name='origin']"; // xpath

    private static final String FIELD_DESTINATION = "//input[@name='destination']";

    private static final String LINK_ORIGIN = "//a[contains(text(),'PRG')]";

    private static final String LINK_DESTINATION = "//a[contains(text(),'VCE')]";

    private static final String LINK_FINDFLIGHTS = "//form[@name='vm.searchSubmit']//a";

    private static final String CALENDAR = "//div[contains(@class,'group-last')]";

    private static final String CAL_START_DATES = "//div[contains(@class,'group-first')]//table//td[@data-handler='selectDay']/a";

    private static final String CAL_RETURN_DATES = "//div[contains(@class,'group-last')]//table//td[@data-handler='selectDay']/a";

    private static final String CHILDREN = "//select[@name='children']";

    private static final String LABEL_ORIGIN_FLIGHT = "div.departure"; // css-selector

    private static final String LABEL_RETURN_FLIGHT = "div.return";

    private static final String CHILDREN_COUNT = "number:1";

    public static void doLogin(WebDriver driver, String email, String password) {
	ActionUtility.waitForPageLoaded(driver);
	By SignIn = By.className(LINK_SIGNIN);
	ActionUtility.waitForElementClickable(driver, 3, SignIn);
	driver.findElement(SignIn).click();
	driver.findElement(By.id(INPUT_EMAIL)).sendKeys(email);
	driver.findElement(By.id(INPUT_PASSWORD)).sendKeys(email);
	driver.findElement(By.xpath(BUTTON_SIGNIN)).click();
	ActionUtility.waitForElementVisible(driver, 3, By.id(LINK_YOUR_PROFILE));
    }

    public static void addOriginReturnLocation(WebDriver driver) throws InterruptedException {
	ActionUtility.waitForPageLoaded(driver);
	By AngularElement = By.xpath(LINK_FINDFLIGHTS);
	ActionUtility.waitForElementVisible(driver, 6, AngularElement);
	// ENTER THE ORIGIN LOCATION
	try {
	    ActionUtility.waitForElementClickable(driver, 6, By.xpath(FIELD_ORIGIN));
	    driver.findElement(By.xpath(FIELD_ORIGIN)).click();
	    driver.findElement(By.xpath(LINK_ORIGIN)).click();
	} catch (StaleElementReferenceException e) {
	    System.out.println("Element reloaded in DOM" + e);
	} catch (ElementNotVisibleException e) {
	    System.out.println("Element not visible in DOM" + e);
	}
	System.out.println("The Origin location is: PRG");
	// ENTER THE DESTINATION LOCATION
	ActionUtility.waitForPageLoaded(driver);
	try {
	    ActionUtility.waitForElementClickable(driver, 6, By.xpath(FIELD_DESTINATION));
	    driver.findElement(By.xpath(FIELD_DESTINATION)).click();
	    driver.findElement(By.xpath(LINK_DESTINATION)).click();
	} catch (StaleElementReferenceException e) {
	    System.out.println("Element reloaded in DOM" + e);
	} catch (ElementNotVisibleException e) {
	    System.out.println("Element not visible in DOM" + e);
	}
	System.out.println("The Return location is: VCE");
    }

    public static String addRandomStartDate(WebDriver driver) {
	// SELECT RANDOM START DATE FROM THE AVAILABLE DATESString
	ActionUtility.waitForElementClickable(driver, 6, By.xpath(CALENDAR));
	List<WebElement> availableStartDates = driver.findElements(By.xpath(CAL_START_DATES));
	Collections.shuffle(availableStartDates);
	String selectedStartDate = availableStartDates.get(0).getText();
	System.out.println("The origin date to be selected is: " + selectedStartDate + " of current month");
	availableStartDates.get(0).click();
	return selectedStartDate;

    }

    public static String addRandomReturnDate(WebDriver driver) {
	// SELECT RANDOM RETURN DATE FROM THE AVAILABLE DATES
	ActionUtility.waitForPageLoaded(driver);
	List<WebElement> availableReturnDates = driver.findElements(By.xpath(CAL_RETURN_DATES));
	Collections.shuffle(availableReturnDates);
	String selectedReturnDate = availableReturnDates.get(0).getText();
	System.out.println("The return date to be selected is: " + selectedReturnDate + " of next month");
	availableReturnDates.get(0).click();
	return selectedReturnDate;
    }

    public static void doFlightSearch(WebDriver driver) {
	// SELECT 1 from Children list box
	ActionUtility.waitForElementVisible(driver, 6, By.xpath(CHILDREN));
	Select childList = new Select(driver.findElement(By.xpath(CHILDREN)));
	childList.selectByValue(CHILDREN_COUNT);
	// Search for Flights
	ActionUtility.waitForElementClickable(driver, 6, By.xpath(LINK_FINDFLIGHTS));
	driver.findElement(By.xpath(LINK_FINDFLIGHTS)).click();

    }

    public static void validateSearchResult(WebDriver driver) throws ElementNotFoundException {

	String originFlight = driver.findElement(By.cssSelector(LABEL_ORIGIN_FLIGHT)).getText();
	System.out.println("*****Outbound Flight Details*****");
	System.out.println(originFlight);
	String returnFlight = driver.findElement(By.cssSelector(LABEL_RETURN_FLIGHT)).getText();
	System.out.println("*****Return Flight Details*****");
	System.out.println(returnFlight);
    }
}
