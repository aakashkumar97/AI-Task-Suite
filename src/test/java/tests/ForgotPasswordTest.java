package tests;

import base.BaseLibrary;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ForgotPasswordPage;

import java.util.Scanner;

public class ForgotPasswordTest extends BaseLibrary {
    ForgotPasswordPage ob;

    @BeforeTest
    @Parameters({"browser", "environment"})
    public void launchUrl(String browser, String environment) {
        LaunchURL(browser, environment);
        ob = new ForgotPasswordPage();
    }

    @Test(priority = 1)
    public void verifyForgotPasswordRedirection() {
        ob.verifyForgotPasswordRedirection();
    }

    @Test(priority = 2)
    public void verifyBackToLogin() {
        ob.verifyBackToLogin();
    }

    @Test(priority = 3)
    public void verifyForgotPassword() {
        ob.verifyForgotPasswordRedirection();
        ob.sendOTP();
    }

    @Test(priority = 4)
    public void validateOTP() {
        ob.validateOTP();
    }

    @Test(priority = 5)
    public void setPassword() {
        ob.setPassword();
    }

    @Test(priority = 6)
    public void validatePassword() {
        ob.validatePassword();
    }

}
