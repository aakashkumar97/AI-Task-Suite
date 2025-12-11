package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ClassTagsPage;

public class ClassTagsTest extends BaseTest {
    ClassTagsPage ob;

    @BeforeClass(alwaysRun = true)
    public void initPages(){
        ob = new ClassTagsPage();
    }

    @Test(priority = 1)
    public void createClass(){
        ob.createClass();
    }
}
