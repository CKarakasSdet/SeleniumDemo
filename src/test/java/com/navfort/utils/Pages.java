package com.navfort.utils;

import com.navfort.pages.DashboardPage;
import com.navfort.pages.LoginPage;

public class Pages {
    private LoginPage loginPage ;
    private DashboardPage dashboardPage ;

    public LoginPage loginPage(){
        if(loginPage == null){
            loginPage = new LoginPage() ;
        }
        return loginPage ;
    }

    public DashboardPage dashboardPage() {
        if(dashboardPage == null){
            dashboardPage = new DashboardPage();
        }
        return dashboardPage ;
    }

}
