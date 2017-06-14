package com.atm.modulefive.webdriver.basics.testdata;

public class Constants {

    private static Constants constants = null;

    private static final String START_URL = "http://www.volotea.com/en";

    private static final String EMAIL = "lakmankat@gmail.com";

    private static final String PASSWORD = "travel2017";

    private static final String PAGETITLE = "VOLOTEA - Cheap flights, offers and plane tickets to Europe";

    private String StartDate;

    private String ReturnDate;

    public static synchronized Constants getInstance() {
	if (constants == null) {
	    constants = new Constants();
	}
	return constants;
    }

    public void setStartdate(String startDate) {
	this.StartDate = startDate;
    }

    public String getStartdate() {
	return StartDate;
    }

    public void setReturndate(String returnDate) {
	this.ReturnDate = returnDate;
    }

    public String getReturndate() {
	return ReturnDate;
    }

    public static String getStartUrl() {
	return START_URL;
    }

    public static String getEmail() {
	return EMAIL;
    }

    public static String getPassword() {
	return PASSWORD;
    }

    public static String getPagetitle() {
	return PAGETITLE;
    }

}
