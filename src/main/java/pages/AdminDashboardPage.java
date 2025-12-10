package pages;

import base.BaseLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminDashboardPage extends BaseLibrary {
    @FindBy(xpath = "//div[@class='user-info']")
    private WebElement userInfo;
    @FindBy(xpath = "//a[@class='logout']")
    private WebElement logout;
    @FindBy(xpath = "//a[@href='/dashboard']")
    private WebElement dashboardNav;
    @FindBy(xpath = "//a[@href='/project-management']")
    private WebElement projectsNav;
    @FindBy(xpath = "//a[@href='/dataset-management']")
    private WebElement datasetNav;
    @FindBy(xpath = "//a[@href='/user']")
    private WebElement userNav;
    @FindBy(xpath = "//a[@href='/class-tags']")
    private WebElement ClassTagsNav;
    @FindBy(xpath = "//a[@href='/quick-stats-panel']")
    private WebElement quickStatsNav;

    public AdminDashboardPage(){
        PageFactory.initElements(driver, this);
    }

}