package com.atm.modulefive.webdriver.advanced.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class CustomProperties {

	static String propertiesFileName = "./src/test/resources/config.Properties";

	public static String getPropertiesByName(String propertyName) {

		File file = new File("./src/test/resources/config.properties");

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop.getProperty(propertyName);
	}

	public static String getEmail() {
		return CustomProperties.getPropertiesByName("email");

	}

}
