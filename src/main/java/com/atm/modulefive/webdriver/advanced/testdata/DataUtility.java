package com.atm.modulefive.webdriver.advanced.testdata;

public class DataUtility {

    private static DataUtility constants = null;
    private static final String START_URL = "http://www.volotea.com/en";
    private static final String EMAIL = "lakmankat@gmail.com";
    private static final String PASSWORD = "travel2017";
    private static final String PAGETITLE = "VOLOTEA - Cheap flights, offers and plane tickets to Europe";
    private static final String selectedStartDate = "30";
    private static final String selectedReturnDate = "14";
    private static final String CHILDREN_COUNT = "number:1";
    private static final String PASSENGER_COUNT = "2";

    public static synchronized DataUtility getInstance() {
	if (constants == null) {
	    constants = new DataUtility();
	}
	return constants;
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

    public static String getSelectedstartdate() {
	return selectedStartDate;
    }

    public static String getSelectedreturndate() {
	return selectedReturnDate;
    }

    public static String getChildrenCount() {
	return CHILDREN_COUNT;
    }

    public static String getPassengerCount() {
	return PASSENGER_COUNT;
    }

}
