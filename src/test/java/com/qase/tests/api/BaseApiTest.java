package com.qase.tests.api;

import com.qase.pageobjects.ProjectsPage;
import org.testng.annotations.BeforeMethod;

public class BaseApiTest {
    public ProjectsPage projectsPage;

    @BeforeMethod(description = "Set up test data")
    public void start() {
        projectsPage = new ProjectsPage();
    }
}