package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\java\\Features\\Put_Delete.feature", glue = {"StepDefinition"},
        monochrome = true, plugin = {"pretty", "html:target/cucumber-reports/cucumber-report1.html","json:target/cucumber-reports/CucumberTestReport1.json"})

public class Put_DeleteRunner {

}
