package tests;

import base.BaseLibrary;
import org.testng.annotations.BeforeTest;
import pages.AnnotatorDashboardPage;

public class AnnotatorDashboardTest extends BaseLibrary {
    AnnotatorDashboardPage ob;

    @BeforeTest
    public void assignObject(){
       ob = new AnnotatorDashboardPage();
    }
}
