package pages;

import base.BaseLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ForgotPasswordPage extends BaseLibrary {
    @FindBy(xpath = "//a[contains(text(),'Forgot Password')]")
    private WebElement forgotPasswordBtn;
    @FindBy(xpath = "//a[contains(text(),'Back to Login')]")
    private WebElement backToLoginBtn;
    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement emailField;
    @FindBy(xpath = "//button[contains(text(),'Send OTP')]")
    private WebElement sendOtpBtn;
    @FindBy(xpath = "//input[@formcontrolname='otp']")
    private WebElement otpField;
    @FindBy(xpath = "//button[contains(text(),'Validate OTP')]")
    private WebElement validateOtpBtn;
    @FindBy(xpath = "//input[@formcontrolname='newPassword']")
    private WebElement newPasswordField;
    @FindBy(xpath = "//input[@formcontrolname='confirmedPassword']")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//button[contains(text(),'Submit')]")
    private WebElement submitButton;
    @FindBy(xpath = "//input[@formcontrolname='username']")
    private WebElement usernameField;
    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;
    @FindBy(xpath = "//div[@class='notyf__message']")
    private WebElement successMessage;

    private String username;
    private String resetPassword;

    public ForgotPasswordPage() {
        PageFactory.initElements(driver, this);
    }

    public void forgotPasswordRedirection(){
        clickWhenReady(forgotPasswordBtn);
        Assert.assertTrue(waitForUrlContains("forgot-password"), "Redirection Failed - Forgot Password page not loaded!");
    }
    public void backToLogin(){
        clickWhenReady(backToLoginBtn);
        Assert.assertTrue(waitForUrlContains("login"), "Login page not loaded!");
    }

    public void sendOTP(String email, String password) {
        username=email;
        if (password==null||password.isBlank()){
        resetPassword = generatePassword(10);
        System.out.println("Password not provided. Random password generated: " + resetPassword);
        }
        else {
            resetPassword = password;
        }
        typeWhenVisible(emailField, email);
        clickWhenReady(sendOtpBtn);
        waitForVisibility(otpField);
    }

    public void validateOTP(){
        String otp = promptUserInput();
        typeWhenVisible(otpField, otp);
        clickWhenReady(validateOtpBtn);
    }

    public void setPassword(){
        typeWhenVisible(newPasswordField, resetPassword);
        typeWhenVisible(confirmPasswordField, resetPassword);
        clickWhenReady(submitButton);
        assertCreation(successMessage);
    }

    public void validatePassword(){
        typeWhenVisible(usernameField, username);
        typeWhenVisible(passwordField, resetPassword);
        clickWhenReady(loginBtn);
        Assert.assertTrue(waitForUrlContains("dashboard"), "Login failed — Password did not Reset!");
    }
}
