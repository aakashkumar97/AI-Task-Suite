package base;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.LoginPage;

public class BaseTest extends BaseLibrary{
    @BeforeClass()
    @Parameters({"browser", "environment", "userType"})
    public void launchUrlAndLogin(String browser, String environment, String userType) {
        String currentClass = this.getClass().getSimpleName();
        LaunchURL(browser, environment);
        if (currentClass.equals("LoginTest")||currentClass.equals("ForgotPasswordTest")) {
            System.out.println("Skipping login for "+ currentClass+".");
            return;
        }

        LoginPage lp = new LoginPage();
        lp.fillCredentials(userType);
        lp.clickLogin();
        lp.validateLogin();
    }

}
