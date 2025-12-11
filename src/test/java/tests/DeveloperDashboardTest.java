package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import pages.DeveloperDashboardPage;

public class DeveloperDashboardTest extends BaseTest {
    DeveloperDashboardPage ob;

    @BeforeClass(alwaysRun = true)
    public void initPages(){
        ob = new DeveloperDashboardPage();
    }
}
