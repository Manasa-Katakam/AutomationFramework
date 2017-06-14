package com.epam.automation.model;

public class Constants {

    private static final String GITHUB_BASE_URL = "http://www.github.com";
    private static final String USERNAME = "testautomationuser";
    private static final String PASSWORD = "Time4Death!";

    private static final String URL = "http://www.google.com";

    public String getGitHubUrl() {
	return URL;
    }

    public static String getGithubBaseUrl() {
	return GITHUB_BASE_URL;
    }

    public static String getUsername() {
	return USERNAME;
    }

    public static String getPassword() {
	return PASSWORD;
    }

}
