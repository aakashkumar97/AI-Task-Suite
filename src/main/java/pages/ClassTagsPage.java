package pages;

import base.BaseLibrary;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClassTagsPage extends BaseLibrary {
    @FindBy(xpath = "//div[@class='user-info']")
    private WebElement userInfo;
    @FindBy(xpath = "//a[@class='logout']")
    private WebElement logout;
    @FindBy(xpath = "//a[@href='/class-tags']")
    private WebElement classTagsNav;
    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchBox;
    @FindBy(xpath = "//button[contains(text(),'+ Class Tag')]")
    private WebElement addClassTagBtn;
    @FindBy(xpath = "//select[@formcontrolname='industry']")
    private WebElement industryType;
    @FindBy(xpath = "//select[@formcontrolname='subSector']")
    private WebElement subSector;
    @FindBy(xpath = "//input[@id='classesType-19719e11-c94b-467c-885a-572f27567520']")
    private WebElement polygon;
    @FindBy(xpath = "//input[@id='classesType-5f6963fb-98f8-4a56-b6f5-1d007b43ed7d']")
    private WebElement box;
    @FindBy(xpath = "//input[@id='classesType-c7201c2b-b53a-41fc-bb02-801824a922b3']")
    private WebElement lineString;
    @FindBy(xpath = "//input[@formcontrolname='classes']")
    private WebElement classes;
    @FindBy(xpath = "//button[@class='btn-cancel']")
    private WebElement cancelBtn;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement createBtn;

    public ClassTagsPage(){
        PageFactory.initElements(driver, this);
    }

    public void createClass(){
        clickWhenReady(classTagsNav);
        clickWhenReady(addClassTagBtn);
        selectByText(industryType, "Highways");
        selectByText(subSector, "Operation and Maintenance");
        clickWhenReady(polygon);
        typeWhenVisible(classes, "automated polygon");
        classes.sendKeys(Keys.ENTER);
        clickWhenReady(box);
        typeWhenVisible(classes, "automated box");
        classes.sendKeys(Keys.ENTER);
        clickWhenReady(lineString);
        typeWhenVisible(classes, "automated linestring");
        classes.sendKeys(Keys.ENTER);
        clickWhenReady(createBtn);
    }


}
