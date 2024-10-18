package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//cucumber -> testNG, JUnit

@CucumberOptions(features="src/test/java/cucumber",glue="Selenium.StepDefinitions",
monochrome=true,tags = "@Regression", plugin={"html:target/cucumber.html"} )
public class TESTNGTestRunner extends AbstractTestNGCucumberTests {

}
