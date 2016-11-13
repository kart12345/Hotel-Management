package hotelmanagement;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/hmp-html-report", "json:target/hmp_report.json"},
        features = "src/test/features/",
tags = "@smoke1")
public class RunTests {

}
