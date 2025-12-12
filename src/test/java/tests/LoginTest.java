package tests;

import base.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    LoginPage ob;

    @BeforeClass(alwaysRun = true)
    public void initPages(){
        ob = new LoginPage();
    }

    @Test(priority = 1)
    @Parameters("userType")
    public void fillCredentials(String userType) {

        System.out.println("Driver is null? " + (driver == null));
        ob.fillCredentials(userType);
    }

    @Test(priority = 2)
    public void clickLoginBtn() {
        ob.clickLogin();
    }

    @Test(priority = 3)
    public void validateLogin() {
        ob.validateLogin();
    }

}