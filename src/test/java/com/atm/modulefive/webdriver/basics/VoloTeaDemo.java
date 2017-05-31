package com.atm.modulefive.webdriver.basics;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VoloTeaDemo {

	private static final String START_URL = "http://www.volotea.com/en";

	private static final String EMAIL = "lakmankat@gmail.com";

	private static final String PASSWORD = "travel2017";

	private static final String LINK_SIGNIN = "switcherLogin";

	private static final String INPUT_EMAIL = "emailLoginForm";

	private static final String INPUT_PASSWORD = "passwordLoginForm";

	private static final String BUTTON_SIGNIN = "//form[@id='loginForm']//a[contains(@class,'voloteaButton')]";

	private static final String LINK_YOUR_PROFILE = "userNavbarOptions";

	private static final String FIELD_ORIGIN = "//input[@name='origin']";

	private static final String FIELD_DESTINATION = "//input[@name='destination']";

	private static final String LINK_ORIGIN = "//a[contains(text(),'PRG')]";

	private static final String LINK_DESTINATION = "//a[contains(text(),'VCE')]";

	private static final String LINK_FINDFLIGHTS = "//form[@name='vm.searchSubmit']//a";

	private static final String PAGETITLE = "VOLOTEA - Cheap flights, offers and plane tickets to Europe";

	private static final String CALENDAR = "//div[contains(@class,'group-last')]";

	private static final String CAL_START_DATES = "//div[contains(@class,'group-first')]//table//td[@data-handler='selectDay']/a";

	private static final String CAL_RETURN_DATES = "//div[contains(@class,'group-last')]//table//td[@data-handler='selectDay']/a";

	private static final String CHILDREN = "//select[@name='children']";

	private static final String LABEL_ORIGIN = "//td[contains(text(),'Outbound')]/..//td[2]";

	private static final String LABEL_RETURN = "//td[contains(text(),'Return')]/..//td[2]";

	private static final String LABEL_PASSENGER_COUNT = "//div[@class='resume-wrapper']//p";

	private static final String LABEL_ORIGIN_FLIGHT = "//div[@class='departure']";

	private static final String LABEL_RETURN_FLIGHT = "//div[@class='return']";

	private WebDriver driver;

	@BeforeClass(description = "Start Browser")
	public void startBrowser() {
		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		driver = new ChromeDriver(capabilities);
		driver.get(START_URL);
	}

	@BeforeClass(dependsOnMethods = "startBrowser", description = "Add implicit wait and maximize window")
	public void addImplicitWait() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		driver.manage().window().maximize();
	}

	@Test(description = "SignIn to VoloTea Application")
	public void VoloTeaSignIn() {
		doLogin(EMAIL, PASSWORD);
		verifyTitle(PAGETITLE); // By Regular Expression
		Assert.assertTrue(isElementPresent(By.id(LINK_YOUR_PROFILE)),
				"Logged in succesfully to with the authenticated USER");
	}

	@Test(dependsOnMethods = "VoloTeaSignIn", description = "Search Flights from Prague to Venice")
	public void SearchFlights() throws InterruptedException {
		doFlightSearch();
		Assert.assertTrue(isElementPresent(By.xpath(LABEL_PASSENGER_COUNT)),
				"Search successful with the best amount selected");
	}

	@Test(dependsOnMethods = "SearchFlights", description = "Validate the Search query made previously")
	public void FlightInformation() {
		validateSearchResult();
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		driver.quit();
	}

	private void doLogin(String email, String password) {
		By SignIn = By.className(LINK_SIGNIN);
		waitForElementClickable(3, SignIn);
		driver.findElement(SignIn).click();
		driver.findElement(By.id(INPUT_EMAIL)).sendKeys(EMAIL);
		driver.findElement(By.id(INPUT_PASSWORD)).sendKeys(PASSWORD);
		driver.findElement(By.xpath(BUTTON_SIGNIN)).click();
		waitForElementVisible(3, By.id(LINK_YOUR_PROFILE));
	}

	private void verifyTitle(String pagetitle) {
		String title = driver.getTitle();
		if (title.matches("VOLO[A-Z]...*")) {
			System.out.println("Reg-Ex matched with the page title");
		} else {
			System.out.println("Reg-Ex not matched with the page title");
		}
		assertEquals(title, pagetitle);
	}

	private void doFlightSearch() throws InterruptedException {
		waitForPageLoaded();
		By AngularElement = By.xpath(LINK_FINDFLIGHTS);
		waitForElementVisible(6, AngularElement);
		// ENTER THE ORIGIN LOCATION
		try {
			waitForElementClickable(6, By.xpath(FIELD_ORIGIN));
			driver.findElement(By.xpath(FIELD_ORIGIN)).click();
			driver.findElement(By.xpath(LINK_ORIGIN)).click();
		} catch (StaleElementReferenceException e) {
			System.out.println("Element reloaded in DOM" + e);
		} catch (ElementNotVisibleException e) {
			System.out.println("Element not visible in DOM" + e);
		}

		// ENTER THE DESTINATION LOCATION
		waitForPageLoaded();
		try {
			waitForElementClickable(6, By.xpath(FIELD_DESTINATION));
			driver.findElement(By.xpath(FIELD_DESTINATION)).click();
			driver.findElement(By.xpath(LINK_DESTINATION)).click();
		} catch (StaleElementReferenceException e) {
			System.out.println("Element reloaded in DOM" + e);
		} catch (ElementNotVisibleException e) {
			System.out.println("Element not visible in DOM" + e);
		}

		// SELECT RANDOM START DATE FROM THE AVAILABLE DATES
		waitForElementClickable(6, By.xpath(CALENDAR));
		List<WebElement> availableStartDates = driver.findElements(By.xpath(CAL_START_DATES));
		Collections.shuffle(availableStartDates);
		String selectedStartDate = availableStartDates.get(0).getText();
		System.out.println("The origin date to be selected is: " + selectedStartDate + " of current month");
		availableStartDates.get(0).click();
		Thread.sleep(1000);

		// SELECT RANDOM START DATE FROM THE AVAILABLE DATES
		waitForElementClickable(6, By.xpath(CALENDAR));
		List<WebElement> availableReturnDates = driver.findElements(By.xpath(CAL_RETURN_DATES));
		Collections.shuffle(availableReturnDates);
		String selectedReturnDate = availableReturnDates.get(0).getText();
		System.out.println("The return date to be selected is: " + selectedReturnDate + " of next month");
		availableReturnDates.get(0).click();

		// SELECT 1 from Children list box
		waitForElementVisible(6, By.xpath(CHILDREN));
		Select childList = new Select(driver.findElement(By.xpath(CHILDREN)));
		childList.selectByValue("number:1");

		// Search for Flights
		waitForElementClickable(6, By.xpath(LINK_FINDFLIGHTS));
		driver.findElement(By.xpath(LINK_FINDFLIGHTS)).click();

		// Verifying the Passenger Count as 2
		Assert.assertTrue(getElementValue(By.xpath(LABEL_PASSENGER_COUNT)).contains("2"),
				"Total number of travellers are 2 as expected");

		// Verifying the Flight results are of the same dates selected
		Assert.assertTrue(getElementValue(By.xpath(LABEL_ORIGIN)).contains(selectedStartDate),
				"In search results page, the origin date is same as selected, i.e: " + selectedStartDate);

		Assert.assertTrue(getElementValue(By.xpath(LABEL_RETURN)).contains(selectedReturnDate),
				"In search results page, the return date is same as selected, i.e: " + selectedReturnDate);

	}

	private void validateSearchResult() {
		// Outbout Flight Details
		if (isElementPresent(By.xpath(LABEL_ORIGIN_FLIGHT)) == true) {
			String originFlight = driver.findElement(By.xpath(LABEL_ORIGIN_FLIGHT)).getText();
			System.out.println("*****Outbound Flight Details*****");
			System.out.println(originFlight);
		} else {
			System.out.println("No default flight selection made for Outbound");
		}

		// Return Flight Details
		if (isElementPresent(By.xpath(LABEL_RETURN_FLIGHT)) == true) {
			String returnFlight = driver.findElement(By.xpath(LABEL_RETURN_FLIGHT)).getText();
			System.out.println("*****Return Flight Details*****");
			System.out.println(returnFlight);
		} else {
			System.out.println("No default flight selection made for Outbound");
		}
	}

	private void waitForElementClickable(int timeout, final By by) {
		new WebDriverWait(driver, timeout).pollingEvery(6, TimeUnit.SECONDS)
				.until(ExpectedConditions.elementToBeClickable(by));
	}

	private void waitForElementVisible(int timeout, final By by) {
		new WebDriverWait(driver, timeout).pollingEvery(6, TimeUnit.SECONDS)
				.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	private boolean isElementPresent(By by) {
		return !driver.findElements(by).isEmpty();
	}

	public String getElementValue(By by) {

		return driver.findElement(by).getText();

	}

}
