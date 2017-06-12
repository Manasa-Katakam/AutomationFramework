package com.epam.automation.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormats {

    /*
     * [IK] This method is never used.
     * 
     */
    public String getCurrentDateAsString() {
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	return dateFormat.format(date);
    }

}
