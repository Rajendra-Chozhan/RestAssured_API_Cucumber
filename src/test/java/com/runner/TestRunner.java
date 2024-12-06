package com.runner;

import com.utilities.ContextLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;



@RunWith(Cucumber.class)
	@CucumberOptions(
			features = "classpath:Features"
			,glue={""}
			,plugin = {"pretty","html:target/cucumber-report/TestRunner.html"}
			,monochrome = true
			,tags = "@GET_API_Negative"
			)

	public class TestRunner  {


	private Logger log  = (Logger) LogManager.getLogger(this.getClass());

	@BeforeSuite
	public void beforeSuite(ITestContext context){

//		System.setProperty("http.proxyHost","proxy.cat.com");
//		System.setProperty("http.proxyPort","80");
//		System.setProperty("http.proxyHost","proxy.cat.com");
//		System.setProperty("http.proxyPort","80");

log.info("Test Started");
	}


	@AfterClass
	public static void teardown(){
		ContextLoader.shutdown();

	}
	}

