package com.tmsandbox.runner;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.tmsandbox.core.DriverManager;

public class CreateSession {
	private static WebDriver driver = null;
	
	// Thread instance that contains WebDriver instance is used to perform browser interactions
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	
	@BeforeSuite
	public static void initDriver() {
		// browser value passed in command line
		String browserName = System.getProperty("browser");
		if(browserName == null) browserName = "chrome";
		
		
		// Get the driver instance
		driver = DriverManager.getWebDriver(browserName);
		webDriver.set(driver);
				
		// Default settings for driver
		getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getWebDriver().manage().window().maximize();
	}
		
	// Returns the webdriver for current thread
	public static WebDriver getWebDriver() {
		System.out.println("WebDriver: " + webDriver.get());
		return webDriver.get();
	}
	
	@AfterSuite
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		getWebDriver().quit();
		DriverManager.quitWebDriver();
	}
}
