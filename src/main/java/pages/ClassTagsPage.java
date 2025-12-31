package pages;

import base.BaseLibrary;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClassTagsPage extends BaseLibrary {

    @FindBy(xpath = "//a[@href='/class-tags']")
    private WebElement classTagsNav;

    @FindBy(xpath = "//button[contains(text(),'+ Class Tag')]")
    private WebElement addClassTagBtn;

    @FindBy(xpath = "//select[@formcontrolname='industry']")
    private WebElement industrySelect;

    @FindBy(xpath = "//select[@formcontrolname='subSector']")
    private WebElement subSectorSelect;

    @FindBy(xpath = "//input[@id='classesType-19719e11-c94b-467c-885a-572f27567520']")
    private WebElement polygon;

    @FindBy(xpath = "//input[@id='classesType-5f6963fb-98f8-4a56-b6f5-1d007b43ed7d']")
    private WebElement box;

    @FindBy(xpath = "//input[@id='classesType-c7201c2b-b53a-41fc-bb02-801824a922b3']")
    private WebElement lineString;

    @FindBy(xpath = "//input[@formcontrolname='classes']")
    private WebElement classes;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement createBtn;

    @FindBy(xpath = "//div[@class='notyf__message']")
    private WebElement successMessage;

    public ClassTagsPage() {
        PageFactory.initElements(driver, this);
    }

    public void createClass() {
        clickWhenReady(classTagsNav);

        createSingleClassTag(
                "Renewable Energy", "Solar",
                "re_solar_polygon", "re_solar_box", "re_solar_linestring"
        );

        createSingleClassTag(
                "Highways", "Under Construction",
                "hw_uc_polygon", "hw_uc_box", "hw_uc_linestring"
        );

        createSingleClassTag(
                "Highways", "Operation and Maintenance",
                "hw_onm_polygon", "hw_onm_box", "hw_onm_linestring"
        );
    }

    private void createSingleClassTag(
            String industry,
            String subSector,
            String polygonName,
            String boxName,
            String lineStringName
    ) {

        clickWhenReady(addClassTagBtn);

        selectByText(industrySelect, industry);
        selectByText(subSectorSelect, subSector);

        clickWhenReady(polygon);
        typeWhenVisible(classes, polygonName);
        classes.sendKeys(Keys.ENTER);

        clickWhenReady(box);
        typeWhenVisible(classes, boxName);
        classes.sendKeys(Keys.ENTER);

        clickWhenReady(lineString);
        typeWhenVisible(classes, lineStringName);
        classes.sendKeys(Keys.ENTER);

        clickWhenReady(createBtn);
        assertMessage(successMessage);
    }
}
