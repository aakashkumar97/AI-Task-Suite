package tests;

import base.BaseLibrary;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.UsersPage;

public class UsersTest extends BaseLibrary {
    UsersPage ob;

    @BeforeTest
    public void assignObject(){
        ob = new UsersPage();
    }

    @Test(priority = 1)
    public void createUser(){
        ob.createUser();
    }
}
