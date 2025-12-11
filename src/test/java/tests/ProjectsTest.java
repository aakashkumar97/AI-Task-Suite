package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProjectsPage;

public class ProjectsTest extends BaseTest {
    ProjectsPage ob;

    @BeforeClass(alwaysRun = true)
    public void initPages(){
        ob = new ProjectsPage();
    }

    @Test(priority = 1)
    public void createProject(){
        ob.createProject();
    }
}
