package framework.browsers;

import framework.logger.MyLogger;
import framework.utils.PropertiesManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BrowserActions {

    private static MyLogger LOGGER = new MyLogger();
    private static WebDriver webDriver;

    public BrowserActions() {
    }

    public static WebDriver getBrowser() {
        if (webDriver == null) {
            webDriver = BrowserFactory.chooseBrowser();
        }
        return webDriver;
    }

    public static void openPage() {
        getBrowser().manage().window().maximize();
        getBrowser().get(PropertiesManager.getProperties("URL"));
        LOGGER.info("start page is opened");
        int timeout = Integer.parseInt(PropertiesManager.getProperties("timeout"));
        getBrowser().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        getBrowser().manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
        getBrowser().manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
    }

    public static void goToPage(String url) {
        getBrowser().manage().window().maximize();
        getBrowser().get(url);
        LOGGER.info(url + " page is opened");
        int timeout = Integer.parseInt(PropertiesManager.getProperties("timeout"));
        getBrowser().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        getBrowser().manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
        getBrowser().manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
        getBrowser().manage().window().maximize();
    }

    public static void backBrowser() {
        getBrowser().navigate().back();
    }

    public static void switchToFrame(WebElement webElement) {
        getBrowser().switchTo().frame(webElement);
    }

    public static void switchToDefaultContent() {
        getBrowser().switchTo().defaultContent();
    }

    public static void quitBrowser() {
        getBrowser().quit();
        webDriver = null;
        LOGGER.info("browser is closed");
    }

    public static String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public static void takeScreenshot(String path) {
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(path));
            LOGGER.info("Screenshot is saved  = " + path);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
    }
}
