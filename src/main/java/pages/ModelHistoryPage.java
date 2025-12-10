package pages;

import base.BaseLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModelHistoryPage extends BaseLibrary {
    @FindBy(xpath = "//div[@class='user-info']")
    private WebElement userInfo;
    @FindBy(xpath = "//a[@class='logout']")
    private WebElement logout;
    @FindBy(xpath = "//a[@href='/model-history']")
    private WebElement modelHistoryNav;
    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchBox;
    @FindBy(xpath = "//button[@class='see-more']")
    private WebElement seeMore;
    @FindBy(xpath = "//button[@class='dropdown-btn']")
    private WebElement dropDownBtn;
    @FindBy(xpath = "//button[text()=' Train Data ']")
    private WebElement trainData;
    @FindBy(xpath = "//button[text()=' Train Model ']")
    private WebElement trainModel;
    @FindBy(xpath = "//span[@class='close-btn material-symbols-outlined']")
    private WebElement closeModal;
    @FindBy(xpath = "//span[@class='arrow-btn left material-symbols-outlined']")
    private WebElement prevBtn;
    @FindBy(xpath = "//span[@class='arrow-btn right material-symbols-outlined']")
    private WebElement nextBtn;

    public ModelHistoryPage(){
        PageFactory.initElements(driver, this);
    }

    public void seeModelDetails(){
        waitForClick(modelHistoryNav);
        waitForClick(seeMore);
        for (int i=0; i<3; i++){
            waitForClick(nextBtn);
        }
        for (int i=0; i<3; i++){
            waitForClick(prevBtn);
        }
        waitForClick(closeModal);
    }

}
