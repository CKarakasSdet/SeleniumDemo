package com.navfort.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Driver {

    private static WebDriver driver;

    private Driver() {
    }

    public synchronized static WebDriver getDriver(String browser) {

        if (driver == null) {

            browser = browser == null ? ConfigurationReader.getProperty("browser") : browser;

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "chromeHeadless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;

                default:
                    throw new RuntimeException("Illegal browser type!");
            } // end of switch
        } // end of if
        return driver;
    }

    public static WebDriver getDriver() {

        return getDriver(null);
    }

    public  static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
