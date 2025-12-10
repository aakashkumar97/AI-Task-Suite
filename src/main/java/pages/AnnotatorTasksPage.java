package pages;

import base.BaseLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AnnotatorTasksPage extends BaseLibrary {
    @FindBy(xpath = "//a[@href='/my-task']")
    private WebElement tasksNav;
    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchBox;

    public AnnotatorTasksPage(){
        PageFactory.initElements(driver, this);
    }
}
