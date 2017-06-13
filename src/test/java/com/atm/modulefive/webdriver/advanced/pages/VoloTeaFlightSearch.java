package com.atm.modulefive.webdriver.advanced.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.atm.modulefive.webdriver.advanced.utils.ActionUtility;
import com.atm.modulefive.webdriver.advanced.utils.DataUtility;

public class VoloTeaFlightSearch extends VoloTeaAbstract {

	public VoloTeaFlightSearch(WebDriver driver) {
		super(driver);
	}

	private final By origin = By.xpath("//input[@name='origin']");
	private final By retrn = By.xpath("//input[@name='destination']");
	private final By originLocation = By.xpath("//a[contains(text(),'PRG')]");
	private final By returnLocation = By.xpath("//a[contains(text(),'VCE')]");
	private final By calen = By.xpath("//div[contains(@class,'group-last')]");
	private final By originDate = By
			.xpath("//div[contains(@class,'group-first')]//table//td[@data-handler='selectDay']/a[text()=30]");
	private final By returnDate = By
			.xpath("//div[contains(@class,'group-last')]//table//td[@data-handler='selectDay']/a[text()=14]");
	private final By child = By.xpath("//select[@name='children']");
	private final By fingFlight = By.xpath("//form[@name='vm.searchSubmit']//a");

	public VoloTeaFlightSummary addOriginReturnDetails() {
		WebElement FIELD_ORIGIN = driver.findElement(origin);
		ActionUtility.waitForElementClickable(driver, 10, FIELD_ORIGIN);
		ActionUtility.waitForPageLoaded(driver);
		System.out.println("Entering Origin Location as PRG");
		FIELD_ORIGIN.click();
		WebElement LINK_ORIGIN = driver.findElement(originLocation);
		LINK_ORIGIN.click();
		ActionUtility.waitForPageLoaded(driver);
		System.out.println("Entering Origin Location as VCE");
		WebElement FIELD_DESTINATION = driver.findElement(retrn);
		FIELD_DESTINATION.click();
		WebElement LINK_DESTINATION = driver.findElement(returnLocation);
		LINK_DESTINATION.click();
		WebElement CALENDAR = driver.findElement(calen);
		ActionUtility.waitForElementClickable(driver, 6, CALENDAR);
		System.out.println("Entering Origin Date as: " + DataUtility.getSelectedstartdate());
		WebElement CAL_START_DATES = driver.findElement(originDate);
		CAL_START_DATES.click();
		ActionUtility.waitForPageLoaded(driver);
		System.out.println("Entering Origin Date as: " + DataUtility.getSelectedreturndate());
		WebElement CAL_RETURN_DATES = driver.findElement(returnDate);
		CAL_RETURN_DATES.click();
		return new VoloTeaFlightSummary(driver);
	}

	public VoloTeaFlightSummary doFlightSearch(String count) {
		System.out.println("Selecting number of children as: " + DataUtility.getChildrenCount());
		WebElement CHILDREN = driver.findElement(child);
		Select childList = new Select(CHILDREN);
		childList.selectByValue(count);
		WebElement LINK_FINDFLIGHTS = driver.findElement(fingFlight);
		ActionUtility.waitForElementClickable(driver, 6, LINK_FINDFLIGHTS);
		LINK_FINDFLIGHTS.click();
		System.out.println("Clicked on Find Flights.... Retriving the results for the search query made");
		ActionUtility.waitForPageLoaded(driver);
		return new VoloTeaFlightSummary(driver);

	}
	
	public boolean flightSearchCorrect() throws InterruptedException {
		ActionUtility.waitForPageLoaded(driver);
		WebElement LINK_FINDFLIGHTS = driver.findElement(fingFlight);
		return LINK_FINDFLIGHTS.isDisplayed();
	}

}
