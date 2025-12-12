package pages;

import base.BaseLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class QuickStatsPage extends BaseLibrary {
    @FindBy(xpath = "//div[@class='user-info']")
    private WebElement userInfo;
    @FindBy(xpath = "//a[@class='logout']")
    private WebElement logout;
    @FindBy(xpath = "//a[@href='/quick-stats-panel']")
    private WebElement quickStatsNav;
    @FindBy(xpath = "//div[@class='refresh']")
    private WebElement refresh;
    @FindBy(xpath = "//div[@class='loader-overlay']")
    private WebElement loaderOverlay;

    public QuickStatsPage(){
        PageFactory.initElements(driver, this);
    }

    public void redirectToQuickStatsPage(){
        clickWhenReady(quickStatsNav);
        waitForVisibility(loaderOverlay);
        waitForInvisibility(loaderOverlay);
        Assert.assertTrue(waitForUrlContains("quick-stats-panel"), "Quick stats panel not loaded!");
    }

    public void refreshStats(){
        clickWhenReady(refresh);
    }
}
