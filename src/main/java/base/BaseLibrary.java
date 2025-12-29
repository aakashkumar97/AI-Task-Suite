package base;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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


    public void LaunchURL(String browser, String environment, boolean isHeadless) {

        String url = switch (environment.toLowerCase()) {
            case "dev" -> getProperty("devURL");
            case "stage" -> getProperty("stageURL");
            default -> throw new IllegalArgumentException("Invalid Environment " + environment);
        };

        switch (browser.toLowerCase()) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-features=PasswordManager");

                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }

                chromeOptions.setExperimentalOption("prefs", Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_leak_detection", false
                ));

                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-features=PasswordManager");

                if (isHeadless) {
                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("--window-size=1920,1080");
                }

                driver = new EdgeDriver(edgeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-features=PasswordManager");

                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--width=1920");
                    firefoxOptions.addArguments("--height=1080");
                }

                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.get(url);

        if (!isHeadless) {
            driver.manage().window().maximize();
        } else {
            driver.manage().window().setSize(new Dimension(1920, 1080));
        }
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
    public void waitForUploadToComplete(WebElement uploadLoader, int maxWaitMinutes) {
        new WebDriverWait(driver, Duration.ofMinutes(maxWaitMinutes))
                .pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.invisibilityOf(uploadLoader));
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
    public String generatePassword(int length) {

        if (length < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 characters");
        }

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+{}[]|:;<>,.?/";

        // Ensure at least one of each requirement
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(special.charAt(random.nextInt(special.length())));

        // Remaining characters (random mix)
        String allChars = upper + lower + digits + special;

        for (int i = 4; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        // Shuffle to avoid predictable pattern (Upper-lower-digit-special)
        List<Character> pwdChars = password.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        Collections.shuffle(pwdChars);

        StringBuilder finalPassword = new StringBuilder();
        pwdChars.forEach(finalPassword::append);

        return finalPassword.toString();
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

    @Override
    public void assertCreation(WebElement successMessage) {
        waitForVisibility(successMessage);
        String actualMsg = successMessage.getText().trim();

        System.out.println("Success Message: " + actualMsg);

        Assert.assertTrue(
                actualMsg.toLowerCase().contains("success"),
                "Expected success message not shown!"
        );
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
