package tests;

import base.BaseLibrary;
import org.testng.annotations.BeforeTest;
import pages.AnnotatorTasksPage;

public class AnnotatorTasksTest extends BaseLibrary {
    AnnotatorTasksPage ob;

    @BeforeTest
    public void assignObject(){
        ob = new AnnotatorTasksPage();
    }
}
