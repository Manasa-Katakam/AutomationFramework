package com.atm.modulefive.webdriver.advanced.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.atm.modulefive.webdriver.advanced.testdata.DataUtility;
import com.atm.modulefive.webdriver.advanced.utils.ActionUtility;

public class VoloTeaFlightSearch extends VoloTeaAbstract {

    public VoloTeaFlightSearch(WebDriver driver) {
	super(driver);
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

    @FindBy(xpath = "//div[contains(@class,'group-last')]//table//td[@data-handler='selectDay']/a[text()=14]")
    private WebElement CAL_RETURN_DATES;

    @FindBy(xpath = "//select[@name='children']")
    private WebElement CHILDREN;

    @FindBy(xpath = "//form[@name='vm.searchSubmit']//a")
    private WebElement LINK_FINDFLIGHTS;

    public VoloTeaFlightSummary addOriginReturnDetails() throws InterruptedException {
	ActionUtility.waitForSync();
	ActionUtility.waitForElementClickable(driver, 6, FIELD_ORIGIN);
	System.out.println("Entering Origin Location as PRG");
	FIELD_ORIGIN.click();
	LINK_ORIGIN.click();
	ActionUtility.waitForSync();
	System.out.println("Entering Origin Location as VCE");
	FIELD_DESTINATION.click();
	LINK_DESTINATION.click();
	ActionUtility.waitForElementClickable(driver, 6, CALENDAR);
	System.out.println("Entering Origin Date as: " + DataUtility.getSelectedstartdate());
	CAL_START_DATES.click();
	ActionUtility.waitForSync();
	System.out.println("Entering Origin Date as: " + DataUtility.getSelectedreturndate());
	CAL_RETURN_DATES.click();
	return new VoloTeaFlightSummary(driver);
    }

    public VoloTeaFlightSummary doFlightSearch(String count) throws InterruptedException {
	System.out.println("Selecting number of children as: " + DataUtility.getChildrenCount());
	Select childList = new Select(CHILDREN);
	childList.selectByValue(count);
	ActionUtility.waitForElementClickable(driver, 6, LINK_FINDFLIGHTS);
	LINK_FINDFLIGHTS.click();
	System.out.println("Clicked on Find Flights.... Retriving the results for the search query made");
	ActionUtility.waitForSync();
	return new VoloTeaFlightSummary(driver);

    }

    public boolean flightSearchCorrect() {
	return LINK_FINDFLIGHTS.isDisplayed();
    }

}
