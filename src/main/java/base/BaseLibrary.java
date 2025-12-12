package base;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class BaseLibrary implements ApplicationUtility, ExcelUtility, PropertyUtility, ScreenshotUtility, WaitUtility {

    protected static WebDriver driver = null;
    private static Properties prop;

    // Load config.properties
    static {
        try {
            Path propPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "config.properties");
            prop = new Properties();
            prop.load(new FileInputStream(propPath.toFile()));
        } catch (Exception e) {
            System.out.println("Issue loading config.properties: " + e.getMessage());
        }
    }


    public void LaunchURL(String browser, String environment) {
        // Initialize URL
        String url = switch (environment.toLowerCase()) {
            case "dev" -> getProperty("devURL");
            case "stage" -> getProperty("stageURL");
            default -> throw new IllegalArgumentException("Invalid Environment " + environment);
        };
        // Initialize browser
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-features=PasswordManager");
                // options.addArguments("--headless=new");      //Headless Mode
                options.addArguments("--window-size=1920,1080");
                options.setExperimentalOption("prefs", Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_leak_detection", false
                ));
                driver = new ChromeDriver(options);
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.get(url);
        driver.manage().window().maximize();
    }

    private WebDriverWait explicitWait() {
        int waitTime = Integer.parseInt(getProperty("explicitWait"));
        return new WebDriverWait(driver, Duration.ofSeconds(waitTime));
    }

    @Override
    public void clickWhenReady(WebElement ele) {
        explicitWait().until(ExpectedConditions.elementToBeClickable(ele));
        ele.click();
    }

    @Override
    public void typeWhenVisible(WebElement ele, String text) {
        explicitWait().until(ExpectedConditions.visibilityOf(ele));
        ele.sendKeys(text);
    }

    @Override
    public boolean waitForUrlContains(String partialUrl) {
        try {
            return explicitWait().until(ExpectedConditions.urlContains(partialUrl));
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Override
    public void waitForInvisibility(WebElement ele) {
        explicitWait().until(ExpectedConditions.invisibilityOf(ele));
    }

    @Override
    public void waitForVisibility(WebElement ele) {
        explicitWait().until(ExpectedConditions.visibilityOf(ele));
    }

    @Override
    public void waitForAlert() {
        explicitWait().until(ExpectedConditions.alertIsPresent());
    }

    @Override
    public void getScreenshot(String folderName, String fileName) {
        Path screenshotDir = Paths.get(System.getProperty("user.dir"), getProperty("screenshotPath"), folderName);
        Path filePath = screenshotDir.resolve(fileName + ".png");

        try {
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, filePath.toFile());
            System.out.println("Screenshot saved to: " + filePath);

        } catch (Exception e) {
            System.out.println("Issue in getScreenshot: " + e.getMessage());
        }
    }

    @Override
    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    @Override
    public String getCellValue(int sheetIndex, int rowIndex, int colIndex) {
        Path excelPath = Paths.get(System.getProperty("user.dir"), getProperty("excelFilePath"));
        try (FileInputStream fis = new FileInputStream(excelPath.toFile());
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            return sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();

        } catch (Exception e) {
            System.out.println("Issue in getCellValue: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void doubleClick(WebElement ele) {
        Actions act = new Actions(driver);
        act.doubleClick(ele).perform();
    }

    @Override
    public void rightClick(WebElement ele) {
        Actions act = new Actions(driver);
        act.contextClick(ele).perform();
    }

    @Override
    public void clickUsingActions(WebElement ele) {
        Actions act = new Actions(driver);
        act.click(ele).perform();
    }

    @Override
    public void moveToElement(WebElement ele) {
        Actions act = new Actions(driver);
        act.moveToElement(ele).build().perform();
    }

    @Override
    public void switchToTab(int tabIndex) {
        List<String> list = new ArrayList<>(driver.getWindowHandles());
        if (tabIndex < list.size()) driver.switchTo().window(list.get(tabIndex));
        else System.out.println("Tab index out of bounds: " + tabIndex);
    }

    @Override
    public void uploadFile(String filePath) {
        try {
            // validate file exists
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Upload failed: File does not exist → " + filePath);
                return;
            }

            // Copy file path to clipboard
            StringSelection selection = new StringSelection(file.getAbsolutePath());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

            // Robot initialization
            Robot robot = new Robot();
            robot.setAutoDelay(200);

            // Give time for OS dialog to be ready
            robot.delay(800);

            // Detect OS for correct paste command
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("mac")) {
                // CMD + V for macOS
                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_META);
            } else {
                // CTRL + V for Windows/Linux
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
            }
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            robot.delay(500);

            // Press ENTER to confirm file selection
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            System.out.println("File uploaded successfully: " + filePath);

        } catch (Exception e) {
            System.out.println("Issue in uploadFile: " + e.getMessage());
        }
    }

    @Override
    public void selectByIndex(WebElement ele, int index) {
        new Select(ele).selectByIndex(index);
    }

    @Override
    public void selectByText(WebElement ele, String text) {
        new Select(ele).selectByVisibleText(text);
    }

    @Override
    public void selectByValue(WebElement ele, String value) {
        new Select(ele).selectByValue(value);
    }

    @Override
    public void acceptAlert() {
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(200);

            // small wait to ensure popup appears
            robot.delay(1000);

            // If Upload is not the default button, press Tab and press ENTER
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(300);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            System.out.println("Chrome upload dialog accepted.");
        } catch (Exception e) {
            System.out.println("Issue while accepting Chrome upload dialog: " + e.getMessage());
        }
    }

    @Override
    public String promptUserInput() {
        return JOptionPane.showInputDialog(
                null,
                "Please enter the OTP received on Email:",
                "Enter OTP",
                JOptionPane.QUESTION_MESSAGE
        );
    }

    @Override
    public List<String> getDropdownList(WebElement ele) {
        List<String> optionTexts = new ArrayList<>();
        for (WebElement opt : new Select(ele).getOptions()) optionTexts.add(opt.getText());
        return optionTexts;
    }

    @AfterMethod
    public void resultAnalysis(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2");
        int priority = result.getMethod().getPriority();
        String methodName = result.getMethod().getMethodName().replaceAll("([a-z])([A-Z])", "$1 $2");
        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        String fileName = className + File.separator + priority + " " + methodName;

        if (result.getStatus() == ITestResult.SUCCESS) {
            getScreenshot("Pass", fileName);
        } else if (result.getStatus() == ITestResult.FAILURE) {
            getScreenshot("Fail", fileName);
        }
    }
}
