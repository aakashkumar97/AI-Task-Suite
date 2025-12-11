package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ForgotPasswordPage;

public class ForgotPasswordTest extends BaseTest {
    ForgotPasswordPage ob;

    @BeforeClass(alwaysRun = true)
    public void initPages(){
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
    @Parameters("email")
    public void verifyForgotPassword(String email) {
        ob.verifyForgotPasswordRedirection();
        ob.sendOTP(email);
    }

    @Test(priority = 4)
    public void validateOTP() {
        ob.validateOTP();
    }

    @Test(priority = 5)
    @Parameters("password")
    public void setPassword(String password) {
        ob.setPassword(password);
    }

    @Test(priority = 6)
    @Parameters({"email", "password"})
    public void validatePassword(String email, String password) {
        ob.validatePassword(email, password);
    }

}
