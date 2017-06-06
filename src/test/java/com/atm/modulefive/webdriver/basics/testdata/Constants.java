package com.atm.modulefive.webdriver.basics.testdata;

public class Constants {
    
    private static Constants constants = null; // [IK] New code added by me.

    public static final String START_URL = "http://www.volotea.com/en"; // [IK]
									// make
									// private

    public static final String EMAIL = "lakmankat@gmail.com"; // [IK] make
							      // private

    public static final String PASSWORD = "travel2017"; // [IK] make private

    public static final String PAGETITLE = "VOLOTEA - Cheap flights, offers and plane tickets to Europe"; // [IK]
													  // make
													  // private

    private String StartDate; // [IK] make private

    public static String ReturnDate; // [IK] make private
    
    /**
     * [IK] New code, added by me.
     * 
     */
    public static synchronized Constants getInstance() {
	    if( constants == null ) {
		constants = new Constants();
	    }
	    return constants;
	  }

    // public static String getStartdate() { // [IK] here is the original code.
    // return StartDate;
    // }

    public static String getReturndate() {
	return ReturnDate;
    }

    /**
     * 
     * [IK] Here is the modified code.
     * 
     * Rewrite getReturndate() in the same way, using the commented example
     * below
     * 
     * 
     */

    // Start of example ========================

    public void setStartdate(String date) {
	this.StartDate = date;
    }

    public String getStartdate() {
	return StartDate;
    }

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
