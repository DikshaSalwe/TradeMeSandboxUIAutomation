package com.tmsandbox.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.tmsandbox.pageObjects.SandboxPage;
import com.tmsandbox.runner.CreateSession;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefination_QueryUsedCar {
	private WebDriver driver = null;
	private SandboxPage sandboxPage;
	private String url1;
	
	public StepDefination_QueryUsedCar () throws IOException {
		driver = CreateSession.getWebDriver();
		
		this.sandboxPage =new SandboxPage(this.driver);
		this.url1 = "https://www.tmsandbox.co.nz/";
	}

	// Scenario: Query exiting used car
	@Given("I go to {string}")
	public void goToTMSandboxPage(String url) throws Throwable {
		sandboxPage.get(url);
	}

	@When("I query for {string} in searchbox")
	public void queryForItem(String query) {
		sandboxPage.waitForPageToLoad(sandboxPage.getTitle());
		sandboxPage.findElement(sandboxPage.searchInput).sendKeys(query);
	}

	@And("I click on search button")
	public void clickSearchButton() {						
		sandboxPage.clickOnElementUsingActions(sandboxPage.searchButton);
	}
	
	@Then("I verify that {string} item is present")
	public void verifySearchedItemPresent(String expectedText) {
		sandboxPage.waitForPageToLoad(sandboxPage.getTitle());
		String actualText = sandboxPage.findElement(sandboxPage.searchedItem).getText();
		Assert.assertTrue(actualText.contains(expectedText));
	}

	// Scenario: Details of used car
	@Given("I am on search result page")
	public void checkIfOnSearchResultPage() throws Throwable {
		sandboxPage.waitForPageToLoad(sandboxPage.getTitle());
	}
	
	@When("I click on {string} item")
	public void clickOnListItem(String item) throws Throwable {
		sandboxPage.clickOnElementUsingActions(sandboxPage.searchedItem);
	}
	
	@Then("I verify Number plate")
	public void verifyNumberPlate() throws InterruptedException {
		String key = "Number plate";
		String keyXpath = sandboxPage.getXpathStringValue(key);				
		String valueXpath = sandboxPage.getXpathSiblingStringValue(key);
		
		boolean isKeyPresent = sandboxPage.isElementPresent(By.xpath(keyXpath));
		boolean isvaluePresent = sandboxPage.isElementPresent(By.xpath(valueXpath));
		
		Assert.assertEquals(isKeyPresent, isvaluePresent);
	}

	@And("I verify Kilometres")
	public void verifyIfKilometresPresent() throws InterruptedException {
		String key = "Kilometres";
		String keyXpath = sandboxPage.getXpathStringValue(key);				
		String valueXpath = sandboxPage.getXpathSiblingStringValue(key);
		
		boolean isKeyPresent = sandboxPage.isElementPresent(By.xpath(keyXpath));
		boolean isvaluePresent = sandboxPage.isElementPresent(By.xpath(valueXpath));
		
		Assert.assertEquals(isKeyPresent, isvaluePresent);
	}

	@And("I verify Body")
	public void verifyIfBodyPresent() throws InterruptedException {
		String key = "Body";
		String keyXpath = sandboxPage.getXpathStringValue(key);				
		String valueXpath = sandboxPage.getXpathSiblingStringValue(key);
		
		boolean isKeyPresent = sandboxPage.isElementPresent(By.xpath(keyXpath));
		boolean isvaluePresent = sandboxPage.isElementPresent(By.xpath(valueXpath));
		
		Assert.assertEquals(isKeyPresent, isvaluePresent);
	}

	@And("I verify Seats")
	public void verifyIfSeatsPresent() throws InterruptedException {
		String key = "Seats";
		String keyXpath = sandboxPage.getXpathStringValue(key);				
		String valueXpath = sandboxPage.getXpathSiblingStringValue(key);
		
		boolean isKeyPresent = sandboxPage.isElementPresent(By.xpath(keyXpath));
		boolean isvaluePresent = sandboxPage.isElementPresent(By.xpath(valueXpath));
		
		Assert.assertEquals(isKeyPresent, isvaluePresent);
	}

}
