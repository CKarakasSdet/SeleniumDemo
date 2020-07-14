package com.navfort.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    private  static final Logger logger = LogManager.getLogger();

    @FindBy(css ="div[class='loader-mask shown']")
    @CacheLookup
    protected WebElement loaderMask ;

    @FindBy(css = "h1[class='oro-subtitle']")
    protected WebElement pageSubTitle ;

    @FindBy (css="#user-menu > a")
    protected WebElement userMenuName ;

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public String getPageSubTitle(){

        waitUntilLoaderScreenDisappear();
        BrowserUtils.waitForStaleElement(pageSubTitle);
        return pageSubTitle.getText();
    }


    public void waitUntilLoaderScreenDisappear(){
        try{
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Integer.valueOf(ConfigurationReader.getProperty("MEDIUM_WAIT")));
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
            logger.info("Loader mask gone...");
        } catch (Exception e){
            logger.error("Loader mask is NOT present.");
            logger.error(e);
        }

    }


    public void navigateToModule(String tab, String module){
        String tabLocator = "//span[normalize-space()='" + tab + "' and contains(@class, 'title title-level-1')]";
        String moduleLocator = "//span[normalize-space()='" + module + "' and contains(@class, 'title title-level-2')]";
        try{
            BrowserUtils.waitForClickability(By.xpath(tabLocator), Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
            WebElement tabElement = Driver.getDriver().findElement(By.xpath(tabLocator));
            new Actions(Driver.getDriver()).moveToElement(tabElement).pause(200).doubleClick(tabElement).build().perform();

        }catch (Exception e){
            logger.error("Failed to click on :: " + tab);
            logger.error(e);
            BrowserUtils.clickWithWait(By.xpath(tabLocator), Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
        }

        try{
            BrowserUtils.waitForPresenceOfElement(By.xpath(moduleLocator), Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
            BrowserUtils.waitForVisibility(By.xpath(moduleLocator), Integer.valueOf(ConfigurationReader.getProperty("WHORT_WAIT")));
            BrowserUtils.scrollToElement(Driver.getDriver().findElement(By.xpath(moduleLocator)));
            Driver.getDriver().findElement(By.xpath(moduleLocator)).click();
        } catch (Exception e){
            logger.error("Failed to click on :: " + module);
            logger.error(e);
            BrowserUtils.waitForStaleElement(Driver.getDriver().findElement(By.xpath(moduleLocator)));
            BrowserUtils.clickWithTimeOut(Driver.getDriver().findElement(By.xpath(moduleLocator)), Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
        }
    }

    public String getUserMenuName(){
        waitUntilLoaderScreenDisappear();
        return userMenuName.getText();
    }

    public String getPageTitle(){
        waitUntilLoaderScreenDisappear();
        return Driver.getDriver().getTitle();
    }

}










