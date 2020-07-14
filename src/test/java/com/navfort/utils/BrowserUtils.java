package com.navfort.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {

    private static final Logger logger = LogManager.getLogger();

    public static void verifyEquals(String expectedResult, String actualResult){
        if(expectedResult.equals(actualResult)){
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
            System.out.println("Expected result: " + expectedResult);
            System.out.println("Actual result: " + actualResult);
        }
    }

    public static void waitPlease(int seconds){
        try{
            Thread.sleep(seconds * 1000);
        } catch (Exception e){
            logger.error(e);
            System.out.println(e.getMessage());
        }
    }


    public static void openPage(String page, WebDriver driver){
        List<WebElement> listOfExamples = driver.findElements(By.tagName("a"));
        for(WebElement example : listOfExamples){
            if(example.getText().contains(page)){
                example.click();
                break;
            }
        }
    }


    public static void verifyIsDisplayed(WebElement element) {
        if(element.isDisplayed()){
            System.out.println("Passed");
            System.out.println(element.getText() + ": is visible");
        } else {
            System.out.println("Failed");
            System.out.println(element.getText() + ": is not visible");
        }
    }

    public static void scrollToElement(WebElement element){
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public static void clickWithJS(WebElement element){
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void clickWithWait(By by, int attempts){
        int counter = 0 ;
        while (counter < attempts){
            try{
                clickWithJS(Driver.getDriver().findElement(by));
                break;
            } catch (WebDriverException wde){
                logger.error(wde);
                logger.error("Attempt :: " + ++counter);
                waitPlease(1);
            }
        }
    }



    public static void clickWithTimeOut(WebElement element, int timeout){
        for(int i = 0 ; i< timeout; i++){
            try{
                element.click();
                break;
            } catch (WebDriverException wde){
                waitFor(1);
            }
        }
    }

    public static void waitFor(int seconds){
        try{
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }

    public static void switchToWindow(String targetTitle){
        String origin = Driver.getDriver().getWindowHandle();

        for(String handle: Driver.getDriver().getWindowHandles()){
            Driver.getDriver().switchTo().window(handle);
            if(Driver.getDriver().getTitle().equals(targetTitle)){
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }


    public static void hover(WebElement element){
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    public static List<String> getElementsText(List<WebElement> list){
        List<String> elemTexts = new ArrayList<>();
        for(WebElement el : list){
            if(!el.getText().isEmpty()){
                elemTexts.add(el.getText());
            }
        }
        return elemTexts ;
    }

    public static List<String> getElementsTest(By locator) {
        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();

        for(WebElement el : elems){
            if(!el.getText().isEmpty()){
                elemTexts.add(el.getText());
            }
        }
        return elemTexts ;
    }


    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver){
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete") ;
            }
        };

        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error){
            error.printStackTrace();
        }


    }

    public static WebElement waitForClickability(By locator, int timeout){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForClickability(WebElement element, int timeout){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForStaleElement(WebElement element) {
        int y = 0 ;
        while (y<=15){
            try {
                element.isDisplayed();
                break;
            } catch (StaleElementReferenceException st){
                y++ ;
                try{
                    Thread.sleep(200);
                } catch (InterruptedException ie){
                    ie.printStackTrace();
                }
            }
            break;
        }
    }

    public static void waitForPresenceOfElement(By by, long time){
        new WebDriverWait(Driver.getDriver(), time).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForStaleness(By by){
        new WebDriverWait(Driver.getDriver(), Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT"))).
                until(ExpectedConditions.stalenessOf(Driver.getDriver().findElement(by)));
    }





}
