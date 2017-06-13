package com.epam.automation.model;

public class CustomException extends Exception {

    /*
     *  [IK] the method is never used.
     *  [MK] used it in exception handeling of signInGitHub constructor
     */
    public CustomException(String msg) {
	super(msg);
	System.out.println(msg);
    }

}
