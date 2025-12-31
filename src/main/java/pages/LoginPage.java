package pages;

import base.BaseLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends BaseLibrary {
    @FindBy(xpath = "//input[@formcontrolname='username']")
    private WebElement usernameField;
    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;
    @FindBy(xpath = "//a[@type='button']")
    private WebElement forgotPasswordBtn;
    @FindBy(xpath = "//div[@class='user-info']")
    private WebElement userInfo;
    @FindBy(xpath = "//a[@class='logout']")
    private WebElement logout;
    @FindBy(xpath = "//div[@class='notyf__message']")
    private WebElement successMessage;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void fillCredentials(String userType) {
        String username = switch (userType.toLowerCase()) {
            case "admin" -> getProperty("adminUsername");
            case "annotator" -> getProperty("annotatorUsername");
            case "developer" -> getProperty("developerUsername");
            default -> throw new IllegalArgumentException("Invalid userType: " + userType);
        };
        String password = getProperty("password");
        typeWhenVisible(usernameField, username);
        typeWhenVisible(passwordField, password);
    }

    public void clickLogin() {
        clickWhenReady(loginBtn);
    }

    public void validateLogin() {
        assertMessage(successMessage);
        Assert.assertTrue(waitForUrlContains("dashboard"), "Login failed — dashboard did not load!");
    }

    public void logoutUser() {
        clickWhenReady(userInfo);
        clickWhenReady(logout);
    }

    public void validateLogout() {
        assertMessage(successMessage);
        Assert.assertTrue(waitForUrlContains("login"), "Logout failed — login page did not load!");

    }
}
