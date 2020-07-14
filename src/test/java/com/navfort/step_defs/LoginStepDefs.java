package com.navfort.step_defs;

import com.navfort.utils.BrowserUtils;
import com.navfort.utils.ConfigurationReader;
import com.navfort.utils.Driver;
import com.navfort.utils.Pages;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;


public class LoginStepDefs {
    Pages pages = new Pages();

    @Given("user landed on login page")
    public void user_landed_on_login_page() {
        pages.loginPage().goToLandingPage();
    }

    @When("user logs in as a store manager")
    public void user_logs_in_as_a_store_manager() {
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        pages.loginPage().login(username, password);
    }

    @Then("user verifies that {string} page name is displayed")
    public void user_verifies_that_page_name_is_displayed(String expected) {
        expected = "Dashboard";
        Assert.assertEquals(expected, pages.dashboardPage().getPageSubTitle());
        BrowserUtils.waitFor(1);
    }

    @When("user logs in as a driver")
    public void user_logs_in_as_a_driver() {
        pages.loginPage().goToLandingPage();
        String username = ConfigurationReader.getProperty("driverusername");
        String password = ConfigurationReader.getProperty("driverpassword");
        pages.loginPage().login(username, password);
    }



    @When("user logs in with {string} username and {string} password")
    public void user_logs_in_with_username_and_password(String username, String password) {
        pages.loginPage().goToLandingPage();
        username ="wrong"; password="wrong";
        pages.loginPage().login(username, password);
    }

    @Then("user verifies that {string} warning message is displayed")
    public void user_verifies_that_warning_message_is_displayed(String expected) {

        Assert.assertEquals(expected, pages.loginPage().getErrorMessage());

    }


    @Given("user logs in as a {string}")
    public void user_logs_in_as_a(String role) {
        pages.loginPage().login(role);
    }


}
