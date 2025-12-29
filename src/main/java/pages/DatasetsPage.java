package pages;

import base.BaseLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DatasetsPage extends BaseLibrary {
    @FindBy(xpath = "//div[@class='user-info']")
    private WebElement userInfo;
    @FindBy(xpath = "//a[@class='logout']")
    private WebElement logout;
    @FindBy(xpath = "//a[@href='/dataset-management']")
    private WebElement datasetNav;
    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchBox;
    @FindBy(xpath = "//button[contains(text(),'+ Dataset')]")
    private WebElement addDatasetBtn;
    @FindBy(xpath = "//input[@formcontrolname='dataset_name']")
    private WebElement datasetNameField;
    @FindBy(xpath = "//button[@class='browser-file']")
    private WebElement browseBtn;
    @FindBy(xpath = "//button[@class='btn-cancel']")
    private WebElement cancelBtn;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement nextBtn;
    @FindBy(xpath = "//button[@class='backToForm']")
    private WebElement backBtn;
    @FindBy(xpath = "//button[@class='browser-file']")
    private WebElement addFilesBtn;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement uploadBtn;
    @FindBy(xpath = "//div[@class='add-user-dialog form-modal']")
    private WebElement uploadModal;
    @FindBy(xpath = "//div[@class='notyf__message']")
    private WebElement successMessage;

    public DatasetsPage(){
        PageFactory.initElements(driver, this);
    }

    public void createDataset(){
        String datasetName = getProperty("datasetName");
        clickWhenReady(datasetNav);
        clickWhenReady(addDatasetBtn);
        typeWhenVisible(datasetNameField,datasetName);
        clickWhenReady(browseBtn);
        uploadFile(getProperty("datasetPath"));
        acceptAlert();
        clickWhenReady(nextBtn);
        waitForUploadToComplete(uploadModal,10);
        assertCreation(successMessage);
        typeWhenVisible(searchBox,datasetName);
    }

}
