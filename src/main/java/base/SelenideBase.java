package base;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static utils.TestProperties.*;

public abstract class SelenideBase {

    @BeforeSuite
    public void beforeSuiteSelenideBase() {
        //set up browser settings
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("enable-automation");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-gpu");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        browserCapabilities = new DesiredCapabilities(options);

        fileDownload = PROXY;
        headless = false;
        screenshots = true;
        //set default time out for Selenide waiter in seconds
        timeout = getIntProperty("timeOutUI") * 1000;
        //set up base url for testing env
        baseUrl = getBaseUrl();
        proxyEnabled = true;
        //set up brouser to run tests;
        browser = getTestProperty("browser");
        browserSize = "1200x768";
        Configuration.screenshots = true;
    }

    //add allure listeners to save screenshots an
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }
}