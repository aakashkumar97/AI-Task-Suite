package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import pages.DeveloperTasksPage;

public class DeveloperTasksTest extends BaseTest {
    DeveloperTasksPage ob;

    @BeforeClass(alwaysRun = true)
    public void initPages(){
        ob = new DeveloperTasksPage();
    }
}
