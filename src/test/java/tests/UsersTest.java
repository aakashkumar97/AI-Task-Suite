package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.UsersPage;

public class UsersTest extends BaseTest {
    UsersPage ob;

    @BeforeClass(alwaysRun = true)
    public void initPages(){
        ob = new UsersPage();
    }

    @Test(priority = 1)
    public void createUser(){
        ob.createUser();
    }
}
