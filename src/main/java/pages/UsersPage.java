package pages;

import base.BaseLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UsersPage extends BaseLibrary {
    @FindBy(xpath = "//div[@class='user-info']")
    private WebElement userInfo;
    @FindBy(xpath = "//a[@class='logout']")
    private WebElement logout;
    @FindBy(xpath = "//a[@href='/user']")
    private WebElement userNav;
    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchBox;
    @FindBy(xpath = "//button[text()=' + User ']")
    private WebElement addUserBtn;
    @FindBy(xpath = "//button[text()=' Assign Scope ']")
    private WebElement assignScopeBtn;
    @FindBy(xpath = "//input[@formcontrolname='firstName']")
    private WebElement firstName;
    @FindBy(xpath = "//input[@formcontrolname='lastName']")
    private WebElement lastName;
    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement email;
    @FindBy(xpath = "//input[@formcontrolname='phone']")
    private WebElement phone;
    @FindBy(xpath = "//select[@formcontrolname='role']")
    private WebElement role;
    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement password;
    @FindBy(xpath = "//input[@formcontrolname='confirmPassword']")
    private WebElement confirmPassword;
    @FindBy(xpath = "//button[@class='btn-cancel']")
    private WebElement cancelBtn;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement createBtn;
    @FindBy(xpath = "//button[@class='filter-btn']")
    private WebElement filterBtn;
    @FindBy(xpath = "//label[text()=' Select All ']/input")
    private WebElement selectAll;
    @FindBy(xpath = "//label[text()=' Active ']/input")
    private WebElement active;
    @FindBy(xpath = "//label[text()=' Inactive ']/input")
    private WebElement inactive;

    public UsersPage() {
        PageFactory.initElements(driver, this);
    }

    public void createUser(){
        clickWhenReady(userNav);
        clickWhenReady(addUserBtn);
        typeWhenVisible(firstName, getProperty("firstName"));
        typeWhenVisible(lastName, getProperty("lastName"));
        typeWhenVisible(email, getProperty("email"));
        typeWhenVisible(phone, getProperty("phone"));
        selectByText(role, getProperty("role"));
        typeWhenVisible(password, getProperty("password"));
        typeWhenVisible(confirmPassword, getProperty("password"));
        clickWhenReady(createBtn);
        typeWhenVisible(searchBox, getProperty("firstName") + " " + getProperty("lastName"));
    }

}
