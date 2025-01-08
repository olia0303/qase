package com.qase.tests.api;

import com.qase.api.ProjectApiAdapter;
import com.qase.api.SuiteApiAdapter;
import com.qase.api.TestCaseApiAdapter;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@Log4j2
public class BaseApiTest {

    public ProjectApiAdapter projectApiAdapter;
    public SuiteApiAdapter suiteApiAdapter;
    public TestCaseApiAdapter testCaseApiAdapter;

    @BeforeSuite
    public void clean() {
        log.info("Clean old test data");
        projectApiAdapter = new ProjectApiAdapter();
        projectApiAdapter.deleteAllProjects();
    }

    @BeforeMethod(description = "Set up test data")
    public void start() {
        projectApiAdapter = new ProjectApiAdapter();
        suiteApiAdapter = new SuiteApiAdapter();
        testCaseApiAdapter = new TestCaseApiAdapter();
    }
}