package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue="stepDefinition", monochrome=true,
tags = "@Regression", plugin= {"html:target/cucumber.html"})
//run all feature files, present in this package and to map them, here is MER step definitions are there.
//Print the results in readable format and generate the report of HTML plugin
//bydefault cucumber can not scan testng assertions, so they created class AbstractTestNGCucumberTests
// cucumber have ability to run junit tests, inbuilt in cucumber
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
