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
    public void VerifyForgotPassword() {
        ob.verifyForgotPasswordRedirection();
        ob.verifyBackToLogin();
        ob.verifyForgotPasswordRedirection();
        ob.sendOTP();
        ob.validateOTP();
        ob.setPassword();
    }

}
