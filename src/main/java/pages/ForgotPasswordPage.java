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
    @FindBy(xpath = "//input[@formcontrolname='username']")
    private WebElement usernameField;
    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    public ForgotPasswordPage() {
        PageFactory.initElements(driver, this);
    }

    public void verifyForgotPasswordRedirection(){
        clickWhenReady(forgotPasswordBtn);
        Assert.assertTrue(waitForUrlContains("forgot-password"), "Forgot Password page not loaded!");
    }
    public void verifyBackToLogin(){
        clickWhenReady(backToLoginBtn);
        Assert.assertTrue(waitForUrlContains("login"), "Login page not loaded!");
    }

    public void sendOTP(String email){
        typeWhenVisible(emailField, email);
        clickWhenReady(sendOtpBtn);
    }

    public void validateOTP(){
        String otp = promptUserInput();
        typeWhenVisible(otpField, otp);
        clickWhenReady(validateOtpBtn);
    }

    public void setPassword(String password){
        typeWhenVisible(newPasswordField, password);
        typeWhenVisible(confirmPasswordField, password);
        clickWhenReady(submitButton);
    }

    public void validatePassword(String email, String password){
        typeWhenVisible(usernameField, email);
        typeWhenVisible(passwordField, password);
        clickWhenReady(loginBtn);
        Assert.assertTrue(waitForUrlContains("dashboard"), "Login failed — Password did not Reset!");
    }
}
