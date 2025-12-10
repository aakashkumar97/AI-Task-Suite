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

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void fillCredentials(String userType) {
        String username = switch (userType.toLowerCase()) {
            case "admin" -> getProperty("adminUsername");
            case "annotator" -> getProperty("annotatorUsername");
            case "developer" -> getProperty("developerUsername");
            default -> throw new IllegalArgumentException("Invalid Environment " + userType);
        };
        String password = getProperty("password");
        waitForType(usernameField, username);
        waitForType(passwordField, password);
    }

    public void clickLogin() {
        waitForClick(loginBtn);
    }

    public void validateLogin() {
        Assert.assertTrue(waitForUrlContains("dashboard"), "Login failed — dashboard not loaded!");
    }
}
