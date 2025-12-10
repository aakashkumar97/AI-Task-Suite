package tests;

import base.BaseLibrary;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseLibrary {
    LoginPage ob;

    @BeforeTest
    @Parameters({"browser", "environment"})
    public void launchUrl(String browser, String environment) {
        LaunchURL(browser, environment);
        ob = new LoginPage();
    }

    @Test(priority = 1)
    @Parameters("userType")
    public void verifyLoginFunctionality(String userType) {
        ob.fillCredentials(userType);
        ob.clickLogin();
        ob.validateLogin();
    }

}
