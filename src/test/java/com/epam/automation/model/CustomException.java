package com.epam.automation.model;

public class CustomException extends Exception {

    /*
     *  [IK] the method is never used.
     *  
     */
    public CustomException(String msg) {
	super(msg);
	System.out.println(msg);
    }

}
