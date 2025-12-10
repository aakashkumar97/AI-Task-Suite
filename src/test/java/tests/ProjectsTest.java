package tests;

import base.BaseLibrary;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.ProjectsPage;

public class ProjectsTest extends BaseLibrary {
    ProjectsPage ob;

    @BeforeTest
    public void assignObject(){
        ob = new ProjectsPage();
    }

    @Test(priority = 1)
    public void createProject(){
        ob.createProject();
    }
}
