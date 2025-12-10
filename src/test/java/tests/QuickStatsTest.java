package tests;

import base.BaseLibrary;
import org.testng.annotations.BeforeTest;
import pages.QuickStatsPage;

public class QuickStatsTest extends BaseLibrary {
    QuickStatsPage ob;

    @BeforeTest
    public void assignObject(){
        ob = new QuickStatsPage();
    }
}
