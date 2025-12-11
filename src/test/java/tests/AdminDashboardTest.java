package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import pages.AdminDashboardPage;

public class AdminDashboardTest extends BaseTest {
    AdminDashboardPage ob;

    @BeforeClass(alwaysRun = true)
    public void initPages() {
        ob = new AdminDashboardPage();
    }


}
