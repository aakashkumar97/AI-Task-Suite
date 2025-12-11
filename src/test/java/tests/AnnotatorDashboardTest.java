package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import pages.AnnotatorDashboardPage;

public class AnnotatorDashboardTest extends BaseTest {
    AnnotatorDashboardPage ob;

    @BeforeClass(alwaysRun = true)
    public void initPages(){
       ob = new AnnotatorDashboardPage();
    }
}
