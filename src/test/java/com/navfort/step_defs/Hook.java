package com.navfort.step_defs;

import com.navfort.utils.ConfigurationReader;
import com.navfort.utils.Driver;
//import io.cucumber.core.gherkin.Scenario;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook {

    @Before
    public void setup(Scenario scenario){
        System.out.println(scenario.getName());
        System.out.println("BEFORE");
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed() ){
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            byte[] image = ts.getScreenshotAs(OutputType.BYTES);
            scenario.embed(image, "image/png");
        }
        Driver.closeDriver();
        System.out.println("AFTER");
    }

}
