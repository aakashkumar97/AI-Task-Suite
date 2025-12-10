package pages;

import base.BaseLibrary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectsPage extends BaseLibrary {
    @FindBy(xpath = "//div[@class='user-info']")
    private WebElement userInfo;
    @FindBy(xpath = "//a[@class='logout']")
    private WebElement logout;
    @FindBy(xpath = "//a[@href='/project-management']")
    private WebElement projectsNav;
    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchBox;
    @FindBy(xpath = "//a[@href='/project-management/annotation']")
    private WebElement annotationTab;
    @FindBy(xpath = "//a[@href='/project-management/developer']")
    private WebElement developerTab;
    @FindBy(xpath = "//button[text()=' + Project ']")
    private WebElement addProjectBtn;
    @FindBy(xpath = "//input[@formcontrolname='project_name']")
    private WebElement projectNameField;
    @FindBy(xpath = "//select[@formcontrolname='sector']")
    private WebElement sectorDropdown;
    @FindBy(xpath = "//select[@formcontrolname='priority']")
    private WebElement priorityDropdown;
    @FindBy(xpath = "//input[@name='start_date']")
    private WebElement startDate;
    @FindBy(xpath = "//input[@name='end_date']")
    private WebElement endDate;
    @FindBy(xpath = "//span[@class='dropdown-btn']")
    private WebElement subsectorDropdown;
    @FindBy(xpath = "//div[text()='Select All']")
    private WebElement selectAll;
    @FindBy(xpath = "//span[@class='dropdown-multiselect__caret']")
    private WebElement closeDropdown;
    @FindBy(xpath = "//select[@formcontrolname='dataset_id']")
    private WebElement datasetSelect;
    @FindBy(xpath = "//button[@class='btn-cancel']")
    private WebElement cancelBtn;
    @FindBy(xpath = "//button[@class='btn btn-success']")
    private WebElement createBtn;

    public ProjectsPage(){
        PageFactory.initElements(driver, this);
    }

    public void createProject(){
        String projectName = getProperty("projectName");
        String datasetName = getProperty("datasetName");
        waitForClick(projectsNav);
        waitForClick(addProjectBtn);
        waitForType(projectNameField, projectName);
        selectByText(sectorDropdown, "Renewable Energy");
        selectByText(priorityDropdown, "High");
        waitForType(startDate, "2025-12-10");
        waitForType(endDate, "2025-12-12");
        waitForClick(subsectorDropdown);
        waitForClick(selectAll);
        waitForClick(closeDropdown);
        selectByText(datasetSelect,datasetName);
        waitForClick(createBtn);
        waitForType(searchBox, projectName);
    }

}