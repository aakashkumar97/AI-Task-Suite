package tests;

import base.BaseLibrary;
import org.testng.annotations.BeforeTest;
import pages.AdminDashboardPage;

public class AdminDashboardTest extends BaseLibrary {
    AdminDashboardPage ob;

    @BeforeTest
    public void assignObject(){
        ob = new AdminDashboardPage();
    }


}
