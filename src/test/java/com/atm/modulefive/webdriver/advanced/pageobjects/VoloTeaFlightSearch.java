package com.atm.modulefive.webdriver.advanced.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.atm.modulefive.webdriver.advanced.configuration.CustomListener;
import com.atm.modulefive.webdriver.advanced.configuration.DefaultDriver;
import com.atm.modulefive.webdriver.advanced.testdata.DataUtility;
import com.atm.modulefive.webdriver.advanced.utils.ActionUtility;

public class VoloTeaFlightSearch {

	CustomListener listener = new CustomListener(); // [IK] Added

	// [IK] Better use singleton [MK] Done
	WebDriver driver = DefaultDriver.initializeDriver();
	Logger logger = LogManager.getRootLogger();

	public VoloTeaFlightSearch(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='origin']")
	private WebElement FIELD_ORIGIN;

	@FindBy(xpath = "//input[@name='destination']")
	private WebElement FIELD_DESTINATION;

	@FindBy(xpath = "//a[contains(text(),'PRG')]")
	private WebElement LINK_ORIGIN;

	@FindBy(xpath = "//a[contains(text(),'VCE')]")
	private WebElement LINK_DESTINATION;

	@FindBy(xpath = "//div[contains(@class,'group-last')]")
	private WebElement CALENDAR;

	@FindBy(xpath = "//div[contains(@class,'group-first')]//table//td[@data-handler='selectDay']/a[text()=30]")
	private WebElement CAL_START_DATES;

	@FindBy(xpath = "//div[contains(@class,'group-first')]//table//td[@data-handler='selectDay']/a[text()=14]")
	private WebElement CAL_RETURN_DATES;

	@FindBy(xpath = "//select[@name='children']")
	private WebElement CHILDREN;

	@FindBy(xpath = "//form[@name='vm.searchSubmit']//a")
	private WebElement LINK_FINDFLIGHTS;

	public VoloTeaFlightSummary addOriginReturnDetails() throws InterruptedException {
		ActionUtility.waitForSync();
		ActionUtility.waitForElementClickable(driver, 6, FIELD_ORIGIN);
		logger.info("Entering Origin Location as PRG");
		FIELD_ORIGIN.click();
		LINK_ORIGIN.click();
		ActionUtility.waitForSync();
		logger.info("Entering Origin Location as VCE");
		FIELD_DESTINATION.click();
		LINK_DESTINATION.click();
		ActionUtility.waitForElementClickable(driver, 6, CALENDAR);
		logger.info("Entering Origin Date as: " + DataUtility.getSelectedstartdate());
		CAL_START_DATES.click();
		ActionUtility.waitForSync();
		logger.info("Entering Origin Date as: " + DataUtility.getSelectedreturndate());
		CAL_RETURN_DATES.click();
		listener.takeIntermediateScreenshot("Test 1"); // [IK] Added to check
														// screenshots
		return new VoloTeaFlightSummary(driver);
	}

	public VoloTeaFlightSummary doFlightSearch(String count) throws InterruptedException {
		logger.info("Selecting number of children as: " + DataUtility.getChildrenCount());
		Select childList = new Select(CHILDREN);
		childList.selectByValue(count);
		ActionUtility.waitForElementClickable(driver, 6, LINK_FINDFLIGHTS);
		LINK_FINDFLIGHTS.click();
		logger.info("Clicked on Find Flights.... Retriving the results for the search query made");
		ActionUtility.waitForSync();
		listener.takeIntermediateScreenshot("Test 2"); // [IK] Added to check
														// screenshots
		return new VoloTeaFlightSummary(driver);

	}

	public boolean flightSearchCorrect() {
		return LINK_FINDFLIGHTS.isDisplayed();
	}

}
