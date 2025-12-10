package tests;

import base.BaseLibrary;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.ClassTagsPage;

public class ClassTagsTest extends BaseLibrary {
    ClassTagsPage ob;

    @BeforeTest
    public void assignObject(){
        ob = new ClassTagsPage();
    }

    @Test(priority = 1)
    public void createClass(){
        ob.createClass();
    }
}
