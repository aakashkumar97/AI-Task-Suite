package pages;

import base.BaseLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ForgotPasswordPage extends BaseLibrary {
    @FindBy(xpath = "//a[@type='button']")
    private WebElement forgotPasswordBtn;
    @FindBy(xpath = "//a[@type='button']")
    private WebElement backToLoginBtn;
    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement emailField;
    @FindBy(xpath = "//button[text()='Send OTP']")
    private WebElement sendOtpBtn;
    @FindBy(xpath = "//input[@formcontrolname='otp']")
    private WebElement otpField;
    @FindBy(xpath = "//button[text()=' Validate OTP ']")
    private WebElement validateOtpBtn;
    @FindBy(xpath = "//input[@formcontrolname='newPassword']")
    private WebElement newPasswordField;
    @FindBy(xpath = "//input[@formcontrolname='confirmedPassword']")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement submitButton;

    public ForgotPasswordPage() {
        PageFactory.initElements(driver, this);
    }

    public void verifyForgotPasswordRedirection(){
        waitForClick(forgotPasswordBtn);
        Assert.assertTrue(waitForUrlContains("forgot-password"), "Forgot Password page not loaded.");
    }
    public void verifyBackToLogin(){
        waitForClick(backToLoginBtn);
        Assert.assertTrue(waitForUrlContains("login"), "Login page not loaded.");
    }

    public void sendOTP(){
        waitForType(emailField, "aakash.kumar@garudauav.com");
        waitForClick(sendOtpBtn);
    }

    public void validateOTP(){
        String otp = getInputFromUser();
        waitForType(otpField, otp);
        waitForClick(validateOtpBtn);
    }

    public void setPassword(){
        waitForType(newPasswordField, "Garuda@1234");
        waitForType(confirmPasswordField, "Garuda@1234");
        waitForClick(submitButton);
    }
}
