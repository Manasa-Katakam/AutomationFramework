package com.atm.modulefive.webdriver.advanced.testdata;

import com.atm.modulefive.webdriver.advanced.utils.CustomProperties;

public class User {

	private String email;
	private String password;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User() {
		this.email = CustomProperties.getPropertiesByName("email");
		this.password = CustomProperties.getPropertiesByName("password");
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
