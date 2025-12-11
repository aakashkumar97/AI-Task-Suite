package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import pages.AnnotatorTasksPage;

public class AnnotatorTasksTest extends BaseTest {
    AnnotatorTasksPage ob;

    @BeforeClass(alwaysRun = true)
    public void initPages(){
        ob = new AnnotatorTasksPage();
    }
}
