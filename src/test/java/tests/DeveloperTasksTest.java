package tests;

import base.BaseLibrary;
import org.testng.annotations.BeforeTest;
import pages.DeveloperTasksPage;

public class DeveloperTasksTest extends BaseLibrary {
    DeveloperTasksPage ob;

    @BeforeTest
    public void assignObject(){
        ob = new DeveloperTasksPage();
    }
}
