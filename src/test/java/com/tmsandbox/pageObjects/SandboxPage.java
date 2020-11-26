package com.tmsandbox.pageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmsandbox.runner.CommonMethods;

public class SandboxPage extends CommonMethods{

	public SandboxPage(WebDriver driver) throws IOException {
		super(driver);
		
	}
	
	
	//Elements
	public By searchInput = By.id("searchString");
	public By searchButton = By.xpath("//button[@value='Search']");
	public By searchedItem = By.xpath("//a/div[@data-listingid='2149213502']");
	
	
	public String getXpathStringValue(String item) {
		return "//label[text()='"+ item +"']";
	}
	
	public String getXpathSiblingStringValue(String item) {
		return "//label[text()='"+ item+"']/parent::div/following-sibling::div";
	}

}
