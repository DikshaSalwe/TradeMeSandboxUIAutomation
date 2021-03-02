package com.tmsandbox.core;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.tmsandbox.helper.DriverType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	private static WebDriver driver;
	private static String CHROME_VERSION = "88.0.4324.104";

	// Create browser driver object
	public static void createWebDriver(String browserName) {
		browserName = browserName.toUpperCase();
		DriverType type = DriverType.valueOf(browserName);
		DesiredCapabilities capability = new DesiredCapabilities();
		
		switch (type) {
			case CHROME: // create chrome driver instance
				WebDriverManager.chromedriver().browserVersion(CHROME_VERSION).setup();
				
				// Set Capability options
				capability.setBrowserName("chrome");
				
				String OS = System.getProperty("os.name");
				if(OS.contains("Windows")) capability.setPlatform(Platform.WIN10);
				else if(OS.contains("Linux")) capability.setPlatform(Platform.LINUX);
				else if(OS.contains("Mac")) capability.setPlatform(Platform.MAC);
							
				Map<String, Object> prefs = new HashMap<String, Object>();
				//Pass the argument 1 to allow and 2 to block notification alert
				prefs.put("profile.default_content_setting_values.notifications", 2);
				
				ChromeOptions options = new ChromeOptions();		
				// set browser specific options here
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--headless");
				//options.setCapability(CapabilityType.BROWSER_NAME, browserName);
				//options.setCapability(CapabilityType.BROWSER_VERSION, CHROME_VERSION);
				
				driver = new ChromeDriver(options);
			break;
//	
//			case FIREFOX: // create firefox driver instance
//				FirefoxDriverManager.getInstance().setup();
//				capability.setBrowserName("firefox");
//				driver = new FirefoxDriver();
//				break;
//				
//			case IE: // create IE driver instance
//				InternetExplorerDriverManager.getInstance().setup();
//				capability.setBrowserName("ie");
//				driver = new InternetExplorerDriver();
//				break;
//				
//			case EDGE: // create MS Edge driver instance
//				EdgeDriverManager.getInstance().setup();
//				capability.setBrowserName("edge");
//				driver = new EdgeDriver();
//				break;
//				
			default: // default - chrome driver instance
				driver = null;
		}
	}

	// quite driver object
	public static void quitWebDriver() {
		if (null != driver) {
			driver.quit();
			driver = null;
		}
	}

	// get driver object
	public static WebDriver getWebDriver(String browserName) {
		if (null == driver) {
			createWebDriver(browserName);
		}
		return driver;
	}
}
