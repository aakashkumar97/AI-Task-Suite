package pages;

import base.BaseLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeveloperDashboardPage extends BaseLibrary {
    @FindBy(xpath = "//div[@class='user-info']")
    private WebElement userInfo;
    @FindBy(xpath = "//a[@class='logout']")
    private WebElement logout;
    @FindBy(xpath = "//a[@href='/developer-dashboard']")
    private WebElement dashboardNav;
    @FindBy(xpath = "//a[@href='/developer-task']")
    private WebElement tasksNav;
    @FindBy(xpath = "//a[@href='/model-history']")
    private WebElement modelHistoryNav;
    @FindBy(xpath = "//a[@href='/quick-stats-panel']")
    private WebElement quickStatsNav;
    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchBox;

    public DeveloperDashboardPage(){
        PageFactory.initElements(driver, this);
    }
}
