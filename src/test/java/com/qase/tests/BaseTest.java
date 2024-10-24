package com.qase.tests;

import com.codeborne.selenide.Selenide;
import com.qase.other.TestData;
import com.qase.pageobjects.LoginPage;
import com.qase.pageobjects.ProjectsPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public LoginPage loginPage;
    public TestData testData;
    public ProjectsPage projectsPage;

    @BeforeMethod(description = "Opening browser")
    public void startBrowser() {
        loginPage = new LoginPage();
        testData = new TestData();
        projectsPage = new ProjectsPage();
    }

    @AfterMethod(description = "Closing browser", alwaysRun = true)
    public void stopBrowser() {
        Selenide.closeWebDriver();
    }
}