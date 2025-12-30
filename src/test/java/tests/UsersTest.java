package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.UsersPage;

public class UsersTest extends BaseTest {
    UsersPage ob;

    @BeforeClass(alwaysRun = true)
    public void initPages() {
        ob = new UsersPage();
    }

    @Test(priority = 1)
    public void goToUsersPage() {
        ob.goToUsers();
    }

    @Test(priority = 2)
    @Parameters("newUserType")
    public void createUser(String newUserType) {
        ob.createUser(newUserType);
    }

}
