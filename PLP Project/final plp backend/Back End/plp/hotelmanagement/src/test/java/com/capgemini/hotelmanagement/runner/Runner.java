package com.capgemini.hotelmanagement.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="Feature",glue= {"com.capgemini.hotelmanagement"},tags= {"@Module4"})
public class Runner {
//tags= {"@Module2"}
	//dryRun=true
	//tags= {"@Module3"}
	//tags= {"@Module5"}
	//tags={"@Module6"}
	//tags= {"@Module2"}
}
