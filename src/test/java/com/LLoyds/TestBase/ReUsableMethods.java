package com.LLoyds.TestBase;


import com.LLoyds.cucumber.stepdefinitions.StepDefinitionsTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.cucumber.listener.Reporter;

import io.restassured.path.json.JsonPath;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import org.junit.Assert;

public class ReUsableMethods {

public ExtentReports extent;
public static ExtentTest scenarioDef;
public static ExtentTest features;
public static  String reportLocation = "C:/Users/jerjose/Documents/RestAssured_Demo/";
private final static Logger logger = Logger.getLogger(StepDefinitionsTest.class.getName());
	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js1 =new JsonPath(response);
		return js1;
	}

	public static String loadPropertiesFile(String attributeName) throws IOException {
		InputStream inputStream;
		Properties prop = new Properties();
		String propFileName = "config.properties";

		inputStream = ReUsableMethods.class.getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		String val = prop.getProperty(attributeName);
return val;
	}
	
	public void compareResponseValues(String expected, String actual, String responseKey) {
		Reporter.addStepLog("Actual Value is  ::" + actual);
		Reporter.addStepLog("Expected Value is  ::" + expected);
		logger.info("Actual Value is  ::" + actual);
		logger.info("Expected Value is  ::" + expected);
		if (expected.equals(actual)) {
			Assert.assertEquals(actual, expected);
			Reporter.addStepLog(Status.PASS + " " + responseKey + " : Expected value : " + expected
					+ " mathches with Actual Value : " + actual);
		} else {
			Reporter.addStepLog(Status.FAIL + " " + responseKey + " : Expected value : " + expected
					+ " do not matches with Actual Value : " + actual);
			Assert.assertEquals(actual, expected);
		}
	}
}
