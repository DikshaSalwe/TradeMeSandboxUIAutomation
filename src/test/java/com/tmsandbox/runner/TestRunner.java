package com.tmsandbox.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "pretty", "html:target/cucumber-reports/cucumber.html",
		"json:target/cucumber-reports/cucmber.json"
		}, 
	features = "src/test/resources/features", 
	monochrome  = true,
	dryRun = false,
	glue = "com.tmsandbox.stepDefinations"
)

public class TestRunner extends AbstractTestNGCucumberTests {
	// Test suit initialization
}
