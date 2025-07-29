package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features/Login.feature",
                 glue={"StepDefinition"},
                 dryRun=false, 
                 monochrome = true, 
                 plugin={"pretty","html:test-output.html"}
                 )


public class TestRun
{

	
		
	
	
}
