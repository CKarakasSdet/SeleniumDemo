package com.navfort.runners;



import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "json:target/cucumber.json",
                "html:target/default-cucumber-reports",
                "rerun:target/rerun.txt" // creating txt file to include failed scenarios
        },
        tags = {

        },

        features = {
              "src/test/java/com/navfort/features"
        },

        glue = {"com/navfort/step_defs"},

        dryRun = false


)

public class testRunner {
}
