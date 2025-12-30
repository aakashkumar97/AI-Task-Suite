package pages;

import base.BaseLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class UsersPage extends BaseLibrary {
    @FindBy(xpath = "//div[@class='user-info']")
    private WebElement userInfo;
    @FindBy(xpath = "//a[@class='logout']")
    private WebElement logout;
    @FindBy(xpath = "//a[@href='/user']")
    private WebElement userNav;
    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchBox;
    @FindBy(xpath = "//button[contains(text(),'+ User')]")
    private WebElement addUserBtn;
    @FindBy(xpath = "//button[contains(text(),'Assign Scope')]")
    private WebElement assignScopeBtn;
    @FindBy(xpath = "//input[@formcontrolname='firstName']")
    private WebElement firstNameInput;
    @FindBy(xpath = "//input[@formcontrolname='lastName']")
    private WebElement lastNameInput;
    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@formcontrolname='phone']")
    private WebElement phoneInput;
    @FindBy(xpath = "//select[@formcontrolname='role']")
    private WebElement roleInput;
    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@formcontrolname='confirmPassword']")
    private WebElement confirmPasswordInput;
    @FindBy(xpath = "//button[@class='btn-cancel']")
    private WebElement cancelBtn;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement createBtn;
    @FindBy(xpath = "//button[@class='filter-btn']")
    private WebElement filterBtn;
    @FindBy(xpath = "//label[contains(text(),'Select All')]/input")
    private WebElement selectAll;
    @FindBy(xpath = "//label[contains(text(),'Active')]/input")
    private WebElement active;
    @FindBy(xpath = "//label[contains(text(),'Inactive')]/input")
    private WebElement inactive;
    @FindBy(xpath = "//div[@class='notyf__message']")
    private WebElement successMessage;

    public UsersPage() {
        PageFactory.initElements(driver, this);
    }

    public void goToUsers() {
        clickWhenReady(userNav);
        Assert.assertTrue(waitForUrlContains("user"), "Redirection Failed - Users page not loaded!");
    }

    public void createUser(String newUserType) {
        String firstName, lastName, email, phone, role, password = getProperty("password");
        switch (newUserType.toLowerCase()) {
            case "admin":
                firstName = getProperty("adminFirstName");
                lastName = getProperty("adminLastName");
                email = getProperty("adminEmail");
                phone = getProperty("adminPhone");
                role = "Super Admin";
                break;
            case "developer":
                firstName = getProperty("developerFirstName");
                lastName = getProperty("developerLastName");
                email = getProperty("developerEmail");
                phone = getProperty("developerPhone");
                role = "Developer";
                break;
            case "annotator":
                firstName = getProperty("annotatorFirstName");
                lastName = getProperty("annotatorLastName");
                email = getProperty("annotatorEmail");
                phone = getProperty("annotatorPhone");
                role = "Annotator";
                break;
            default:
                throw new IllegalArgumentException("Invalid User Type: " + newUserType);

        }

        clickWhenReady(addUserBtn);
        typeWhenVisible(firstNameInput, firstName);
        typeWhenVisible(lastNameInput, lastName);
        typeWhenVisible(emailInput, email);
        typeWhenVisible(phoneInput, phone);
        selectByText(roleInput, role);
        typeWhenVisible(passwordInput, password);
        typeWhenVisible(confirmPasswordInput, password);
        clickWhenReady(createBtn);
        assertMessage(successMessage);
    }
    public void filterUser() {
        typeWhenVisible(searchBox, getProperty("firstName") + " " + getProperty("lastName"));
    }

}
