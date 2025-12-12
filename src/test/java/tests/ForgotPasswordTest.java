package tests;

import base.BaseTest;
import org.testng.annotations.AfterTest;
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
    public void forgotPasswordRedirection() {
        ob.forgotPasswordRedirection();
    }

    @Test(priority = 2)
    public void backToLogin() {
        ob.backToLogin();
    }

    @Test(priority = 3)
    @Parameters({"email", "password"})
    public void sendOTP(String email,  String password) {
        ob.forgotPasswordRedirection();
        ob.sendOTP(email,password);
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
    public void validateNewPassword() {
        ob.validatePassword();
    }

}
