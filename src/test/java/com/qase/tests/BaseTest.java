package com.qase.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.qase.api.ProjectApiAdapter;
import com.qase.other.TestData;
import com.qase.pageobjects.*;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class BaseTest {
    public LoginPage loginPage;
    public TestData testData;
    public ProjectsPage projectsPage;
    public ProjectSettingPage projectSettingPage;
    public SuitesPage suitesPage;
    public TestCasePage testCasePage;
    public ProjectApiAdapter projectApiAdapter;

    @BeforeSuite
    public void clean() {
        log.info("Clean old test data");
        projectApiAdapter.deleteAllProjects();
    }

    @BeforeMethod(description = "Opening browser")
    public void startBrowser() {
        loginPage = new LoginPage();
        testData = new TestData();
        projectsPage = new ProjectsPage();
        projectSettingPage = new ProjectSettingPage();
        suitesPage = new SuitesPage();
        testCasePage = new TestCasePage();
        useChromeWithOptions();
    }

    public void useChromeWithOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        Configuration.browserCapabilities = options;

    }

    @AfterMethod(description = "Closing browser", alwaysRun = true)
    public void stopBrowser() {
        Selenide.closeWebDriver();
    }
}