package com.tmsandbox.runner;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonMethods {
	WebDriver driver = null;
	public final int timeOut = 45;
	
	public CommonMethods(WebDriver driver) throws IOException {
		this.driver = driver;
	}
	
	public void get(String url) {
		driver.get(url);
	}
	
	public void navigate(String url) {
		driver.navigate().to(url);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void clickOnElementUsingActions(By element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(element));
		actions.click().perform();
	}
	
	public void clickOnElementUsingJs(By targetElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argument[0].click();", driver.findElement(targetElement));
	}
	
	// Wait for page to load
	public void waitForPageToLoad(String pageName) {
		String pageLoadStatus;
		JavascriptExecutor js;
		
		do {
			js = (JavascriptExecutor) driver;
			pageLoadStatus = (String)js.executeScript("return document.readyState");
		} while( !pageLoadStatus.equals("complete"));
		
	}
	
	// findElement
	public WebElement findElement(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			return element;
		} catch(NoSuchElementException e) {
			System.out.println(this.getClass().getName()+ "findElement :Element not found" + locator);
			throw new NoSuchElementException();
		}
	}
	
	public List<WebElement> findElements(By locator) {
		try {
			List<WebElement> element = driver.findElements(locator);
			return element;
		} catch(NoSuchElementException e) {
			System.out.println(this.getClass().getName()+ "findElement :Element not found" + locator);
			throw new NoSuchElementException();
		}
	}
	
	// Scroll to element
	public void scrollToElement(By targetElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(targetElement));
	}
	
	// Element present check 
	public Boolean isElementPresent(By targetElement) throws InterruptedException {
		Boolean isPresent = driver.findElements(targetElement).size() > 0;
		return isPresent;
	}
	
	public Boolean isElementNotPresent(By targetElement) throws InterruptedException {
		Boolean isPresent = (driver.findElements(targetElement).size() == 0);
		return isPresent;
	}
	
	// Wait for element to be - clickable, visible
	public boolean waitForElementVisibility(By targetElement) throws TimeoutException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.visibilityOfElementLocated(targetElement));
			return true;
		} catch (Exception e) {
			System.out.println("Element is not visible: " + targetElement);
			System.out.println(e.getMessage());
			throw new TimeoutException();
		}
	}
	
	public boolean waitForElementToBeClickable(By targetElement) throws TimeoutException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.elementToBeClickable(targetElement));
			return true;
		} catch(Exception e) {
			System.out.println("Element is not clickable: " + targetElement);
			System.out.println(e.getMessage());
			throw new TimeoutException();
		}
	}
	
	public boolean waitForInVisibility(By targetElement) throws TimeoutException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(targetElement));
			return true;
		} catch(Exception e ) {
			System.out.println("Element is still visible: " + targetElement);
			System.out.println(e.getMessage());
			throw new TimeoutException();
		}
	}
	
	public void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch(NoAlertPresentException e) {
			throw new NoAlertPresentException();
		}
	}
	
	public void implicitWait(int ms) {
		try {
			Thread.sleep(ms);
		} catch(Exception e) {}
	}
	
	public String getAlertText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			return alertText;
		} catch(NoAlertPresentException e) {
			throw new NoAlertPresentException();
		}
	}
	
	public boolean isAlertPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch(NoAlertPresentException e) {
			throw new NoAlertPresentException();
		}
	}
	
	public void selectValueFromDropdownViaIndex(By element, int valueToSelectedIndex) {
		Select selectFromDropdown = new Select(driver.findElement(element));
		selectFromDropdown.selectByIndex(valueToSelectedIndex);
	}
}
