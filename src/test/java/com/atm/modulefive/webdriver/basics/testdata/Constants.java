package com.atm.modulefive.webdriver.basics.testdata;

public class Constants {

	public static final String START_URL = "http://www.volotea.com/en"; // [IK] make private

	public static final String EMAIL = "lakmankat@gmail.com"; // [IK] make private

	public static final String PASSWORD = "travel2017"; // [IK] make private

	public static final String PAGETITLE = "VOLOTEA - Cheap flights, offers and plane tickets to Europe"; // [IK] make private

	public static String StartDate; // [IK] make private

	public static String ReturnDate; // [IK] make private

	public static String getStartdate() {
		return StartDate;
	}

	public static String getReturndate() {
		return ReturnDate;
	}

    /**
     * Rewrite getStartdate() and getReturndate(), using the commented example
     * below
     * 
     * 
     */

    // Start of example ========================

    // public void getStartdate(String date) {
    // this.StartDate = date;
    // }
    //
    // public String getStartdate() {
    // return StartDate;
    // }

    // End of example ===========================

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
