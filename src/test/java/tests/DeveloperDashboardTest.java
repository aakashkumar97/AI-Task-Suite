package tests;

import base.BaseLibrary;
import org.testng.annotations.BeforeTest;
import pages.DeveloperDashboardPage;

public class DeveloperDashboardTest extends BaseLibrary {
    DeveloperDashboardPage ob;

    @BeforeTest
    public void assignObject(){
        ob = new DeveloperDashboardPage();
    }
}
