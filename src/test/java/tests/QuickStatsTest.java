package tests;

import base.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.QuickStatsPage;

public class QuickStatsTest extends BaseTest {
    QuickStatsPage ob;

    @BeforeClass(alwaysRun = true)
    public void initPages(){
        ob = new QuickStatsPage();
    }

    @Test(priority = 1)
    public void redirectToQuickStatsPage(){
        ob.redirectToQuickStatsPage();
    }

    @Test(priority = 2)
    public void refreshStats(){
        ob.refreshStats();
    }

}
