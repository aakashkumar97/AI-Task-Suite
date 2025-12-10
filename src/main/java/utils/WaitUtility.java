package utils;

import org.openqa.selenium.WebElement;

public interface WaitUtility {
    void waitForClick(WebElement ele);

    void waitForType(WebElement ele, String text);

    boolean waitForUrlContains(String partialUrl);

    void alertIsPresent();

    void waitUntilModalClose(WebElement ele);

}
