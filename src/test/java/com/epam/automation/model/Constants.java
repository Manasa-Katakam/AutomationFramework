package com.epam.automation.model;

public class Constants {

    /*
     * [IK] Better make constants private, but access to them public.
     * 
     */

    public static final String GITHUB_BASE_URL = "http://www.github.com";
    public static final String USERNAME = "testautomationuser";
    public static final String PASSWORD = "Time4Death!";

    /*
     * [IK] Example of what was said above.
     * 
     */
    
    private static final String URL = "http://www.google.com";
    
    public String getGitHubUrl() {
	return URL;
    }

}
