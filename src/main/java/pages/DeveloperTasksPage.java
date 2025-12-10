package pages;

import base.BaseLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeveloperTasksPage extends BaseLibrary {
    @FindBy(xpath = "//div[@class='user-info']")
    private WebElement userInfo;
    @FindBy(xpath = "//a[@class='logout']")
    private WebElement logout;
    @FindBy(xpath = "//a[@href='/developer-task']")
    private WebElement tasksNav;
    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchBox;

    public DeveloperTasksPage(){
        PageFactory.initElements(driver, this);
    }
}
