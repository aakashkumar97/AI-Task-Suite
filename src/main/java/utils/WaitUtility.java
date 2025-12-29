package utils;

import org.openqa.selenium.WebElement;

public interface WaitUtility {
    void clickWhenReady(WebElement ele);

    void typeWhenVisible(WebElement ele, String text);

    boolean waitForUrlContains(String partialUrl);

    void waitForInvisibility(WebElement ele);

    void waitForUploadToComplete(WebElement uploadLoader, int maxWaitMinutes);

    void waitForVisibility(WebElement ele);

    void waitForAlert();

}
