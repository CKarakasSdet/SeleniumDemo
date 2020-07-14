package com.navfort.pages;

import com.navfort.utils.BrowserUtils;
import com.navfort.utils.ConfigurationReader;
import com.navfort.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "prependedInput")
    @CacheLookup
    public WebElement userNameElement;

    @FindBy(name = "_password")
    @CacheLookup
    public WebElement passwordElement ;

    @FindBy(id = "_submit")
    public WebElement loginButtonElement;

    @FindBy(className = "custom-checkbox_icon")
    public WebElement rememberMeElement;

    @FindBy(partialLinkText = "Forgot your password?")
    public WebElement forgotPasswordElement;

    @FindBy(tagName = "h2")
    public WebElement titleElement;

    @FindBy(css = "[class='alert alert-error'] > div")
    public WebElement errorMessageElement;

    public void login(String username, String password) {
        userNameElement.sendKeys(username);
        passwordElement.sendKeys(password);
        loginButtonElement.click();
    }

    public void login(){
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        userNameElement.sendKeys(username);
        passwordElement.sendKeys(password);
        loginButtonElement.click();
    }


    public void login(String role){
        String username = "";
        String password = "";

        if(role.equalsIgnoreCase("driver")){
            username = ConfigurationReader.getProperty("driverusername");
            password = ConfigurationReader.getProperty("driverpassword");
        } else if (role.equalsIgnoreCase("storemanager")){
            username = ConfigurationReader.getProperty("storemanagerusername");
            password = ConfigurationReader.getProperty("storemanagerpassword");
        } else if (role.equalsIgnoreCase("salesmanager")){
            username = ConfigurationReader.getProperty("salesmanagerusername");
            password = ConfigurationReader.getProperty("salesmanagerpassword");
        }

        userNameElement.sendKeys(username);
        passwordElement.sendKeys(password);
        loginButtonElement.click();
    }

    public String getErrorMessage() {
        return errorMessageElement.getText();
    }

    public void clickRememberMe(){
        BrowserUtils.waitForClickability(rememberMeElement, Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
        if( !rememberMeElement.isSelected()){
            rememberMeElement.click();
        }
    }


    public void goToLandingPage(){
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }


}
