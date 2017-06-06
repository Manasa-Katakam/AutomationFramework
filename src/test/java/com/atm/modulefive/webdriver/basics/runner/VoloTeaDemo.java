package com.atm.modulefive.webdriver.basics.runner;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atm.modulefive.webdriver.basics.testdata.Constants;
import com.atm.modulefive.webdriver.basics.utils.ActionUtility;

public class VoloTeaDemo {

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

	private static final String LABEL_ORIGIN = "//td[contains(text(),'Outbound')]/..//strong/..";

	private static final String LABEL_RETURN = "//td[contains(text(),'Return')]/..//strong/..";

	private static final String LABEL_PASSENGER_COUNT = "//div[@class='resume-wrapper']//p";

	private static final String LABEL_ORIGIN_FLIGHT = "div.departure"; // css-selector

	private static final String LABEL_RETURN_FLIGHT = "div.return";

	private static WebDriver driver;

	@BeforeClass(description = "Start Browser") //[IK] I think we can unite two @BeforeClass methods into single one.
	public void startBrowser() {
		System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		driver = new ChromeDriver(capabilities);
		driver.get(Constants.START_URL);
	}

	@BeforeClass(dependsOnMethods = "startBrowser", description = "Add implicit wait and maximize window")
	public void addImplicitWait() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		driver.manage().window().maximize();
	}

	@Test(description = "SignIn to VoloTea Application")
	public void VoloTeaSignIn() {
		doLogin(Constants.EMAIL, Constants.PASSWORD);
		verifyTitle(Constants.PAGETITLE); // By Regular Expression
		Assert.assertTrue(ActionUtility.isElementPresent(driver, By.id(LINK_YOUR_PROFILE)),
				"Logged in succesfully to with the authenticated USER"); //[IK] There should be the message, which will be seen in the logs, if the verification is failed.
		System.out.println("Logged in succesfully to with the authenticated USER"); 
	}

	@Test(dependsOnMethods = "VoloTeaSignIn", description = "Search Flights from Prague to Venice")
	public void SearchFlights() throws InterruptedException {
		addOriginReturnLocation();
//		Constants.StartDate = addRandomStartDate(); // [IK] Original code is here
		
		Constants.getInstance().setStartdate(addRandomStartDate()); // [IK] Modified code - do like this with ReturnDate
		
		Constants.ReturnDate = addRandomReturnDate();
		doFlightSearch();
		Assert.assertTrue(ActionUtility.isElementPresent(driver, By.xpath(LABEL_PASSENGER_COUNT)),
				"Search successful with the best amount selected"); //[IK] There should be the message, which will be seen in the logs, if the verification is failed.
		System.out.println("Completed the Flight Search with specific Details");
	}

	@Test(dependsOnMethods = "SearchFlights", description = "Validate the Search query made previously")
	public void FlightInformation() {
//		validateFlightDatesSelected(Constants.getStartdate(), Constants.getReturndate()); // [IK] Original code
	    validateFlightDatesSelected(Constants.getInstance().getStartdate(), Constants.getReturndate());	// [IK] Modified code. Do like this for getReturndate()	
	    validateSearchResult();
		Assert.assertTrue(ActionUtility.isElementPresent(driver, By.cssSelector(LABEL_ORIGIN_FLIGHT)),
				"Search successful with Flight details being visible"); //[IK] There should be the message, which will be seen in the logs, if the verification is failed.
		System.out.println(
				"Flight Search with given details have been made and the list of available flights are visible");
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		driver.quit();
	}

	// ==============================================================================================
	// [IK] What is below should be moved to a separate class together with the related constants.
	
	private void doLogin(String email, String password) {
		By SignIn = By.className(LINK_SIGNIN);
		ActionUtility.waitForElementClickable(driver, 3, SignIn);
		driver.findElement(SignIn).click();
		driver.findElement(By.id(INPUT_EMAIL)).sendKeys(email);
		driver.findElement(By.id(INPUT_PASSWORD)).sendKeys(email);
		driver.findElement(By.xpath(BUTTON_SIGNIN)).click();
		ActionUtility.waitForElementVisible(driver, 3, By.id(LINK_YOUR_PROFILE));
	}

	private void verifyTitle(String pagetitle) {
		String title = driver.getTitle();
		if (title.matches("VOLO[A-Z]...*")) { // [IK] Seems like we can remove this if-else, as the assertion will check the title.
			System.out.println("Reg-Expression matched with the page title");
		} else {
			System.out.println("Reg-Expression not matched with the page title");
		}
		assertEquals(title, pagetitle);
	}

	private void addOriginReturnLocation() throws InterruptedException {
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

	public static String addRandomStartDate() {
		// SELECT RANDOM START DATE FROM THE AVAILABLE DATESString
		ActionUtility.waitForElementClickable(driver, 6, By.xpath(CALENDAR));
		List<WebElement> availableStartDates = driver.findElements(By.xpath(CAL_START_DATES));
		Collections.shuffle(availableStartDates);
		String selectedStartDate = availableStartDates.get(0).getText();
		System.out.println("The origin date to be selected is: " + selectedStartDate + " of current month");
		availableStartDates.get(0).click();
		return selectedStartDate;

	}
	
	public static String addRandomReturnDate() {
		// SELECT RANDOM RETURN DATE FROM THE AVAILABLE DATES
		ActionUtility.waitForPageLoaded(driver);
		List<WebElement> availableReturnDates = driver.findElements(By.xpath(CAL_RETURN_DATES));
		Collections.shuffle(availableReturnDates);
		String selectedReturnDate = availableReturnDates.get(0).getText();
		System.out.println("The return date to be selected is: " + selectedReturnDate + " of next month");
		availableReturnDates.get(0).click();
		return selectedReturnDate;
	}

	private void doFlightSearch() {
		// SELECT 1 from Children list box
		ActionUtility.waitForElementVisible(driver, 6, By.xpath(CHILDREN));
		Select childList = new Select(driver.findElement(By.xpath(CHILDREN)));
		childList.selectByValue("number:1"); // [IK] Extract constant "number:1" here.
		// Search for Flights
		ActionUtility.waitForElementClickable(driver, 6, By.xpath(LINK_FINDFLIGHTS));
		driver.findElement(By.xpath(LINK_FINDFLIGHTS)).click();

	}

	private void validateFlightDatesSelected(String startDate, String endDate) {
		// Verifying the Flight results are of the same dates selected
		Assert.assertTrue(ActionUtility.getElementValue(driver, By.xpath(LABEL_ORIGIN)).contains(startDate), // [IK] Put assertions to the @Test methods
				"In search results page, the origin date is same as selected, i.e: " + startDate);

		Assert.assertTrue(ActionUtility.getElementValue(driver, By.xpath(LABEL_RETURN)).contains(endDate), // [IK] Put assertions to the @Test methods
				"In search results page, the return date is same as selected, i.e: " + endDate);
	}

	private void validateSearchResult() {
		// Verifying the Passenger Count as 2
		Assert.assertTrue(ActionUtility.getElementValue(driver, By.xpath(LABEL_PASSENGER_COUNT)).contains("2"), // [IK] Put assertions to the @Test methods // [IK] Extract constant "2" here.
				"Total number of travellers are 2 as expected"); 	//[IK] Put assertions to the @Test methods //[IK] There should be the message, which will be seen in the logs, if the verification is failed.

		// Outbout Flight Details
		if (ActionUtility.isElementPresent(driver, By.cssSelector(LABEL_ORIGIN_FLIGHT)) == true) { // [IK] Throw exception here instead of if-else.
			String originFlight = driver.findElement(By.cssSelector(LABEL_ORIGIN_FLIGHT)).getText();
			System.out.println("*****Outbound Flight Details*****");
			System.out.println(originFlight);
		} else {
			System.out.println("No default flight selection made for Outbound");
		}
		// Return Flight Details
		if (ActionUtility.isElementPresent(driver, By.cssSelector(LABEL_RETURN_FLIGHT)) == true) { // [IK] Throw exception here instead of if-else.
			String returnFlight = driver.findElement(By.cssSelector(LABEL_RETURN_FLIGHT)).getText();
			System.out.println("*****Return Flight Details*****");
			System.out.println(returnFlight);
		} else {
			System.out.println("No default flight selection made for Outbound");
		}
	}

}
