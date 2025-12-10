package utils;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface ApplicationUtility {
    void doubleClick(WebElement ele);

    void rightClick(WebElement ele);

    void actionClick(WebElement ele);

    void moveToElement(WebElement ele);

    void switchToTab(int tabIndex);

    void uploadFile(String filePath);

    void selectByIndex(WebElement ele, int index);

    void selectByText(WebElement ele, String text);

    void selectByValue(WebElement ele, String value);

    void acceptBrowserPopup();

    String getInputFromUser();

    List<String> getDropdownList(WebElement ele);

}
