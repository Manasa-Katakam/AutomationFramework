package com.atm.modulefive.webdriver.advanced.driver;

import org.openqa.selenium.WebDriver;

import com.atm.modulefive.webdriver.advanced.utils.Decorator;

public class DefaultDriver {
	
	private static WebDriver driver;
	private static WebDriver decorator;
	
	private DefaultDriver(){
		
	}
	
	public static WebDriver initializeDriver(String browser){
		if(null==driver){
			if("chrome".equalsIgnoreCase(browser)){
				WebDriverCreator create = new ChromeDriverCreator();
				driver = create.factoryMethod();
				decorator = new Decorator(driver);
			}else if ("firefox".equalsIgnoreCase(browser)) {
				WebDriverCreator create = new FirefoxDriverCreator();
				driver = create.factoryMethod();
				decorator = new Decorator(driver);				
			}
		}
		return decorator;
	}

	public static void closeBrowser() {
		if (null != decorator) {
			decorator.quit();
			decorator = null;
		}
	}
}
