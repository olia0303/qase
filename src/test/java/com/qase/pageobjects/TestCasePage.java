package com.qase.pageobjects;

import com.codeborne.selenide.Condition;
import com.qase.elements.QaseSelect;
import com.qase.model.TestCase;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TestCasePage extends BasePage {
    private final String CREATE_CASE_BUTTON = "#create-case-button";
    private final String TEST_CASE_PAGE = "//h3[contains(text(),'Test cases without suite')]";
    private final String TEST_CASE_LINK = "//div[@data-suite-body-id]//div[text()='%s']";
    private final String EDIT_BUTTON = "//span[normalize-space(text())='Edit']";
    private final String EDIT_TEST_CASE_PAGE = "//h1[text()='Edit test case']";
    private final String SAVE_CASE_BUTTON = "#save-case";
    private final String TEST_CASE_NAME = "#title";
    private final String DELETE_BUTTON = "//span[normalize-space(text())='Delete']";
    private final String DELETE_TEST_CASE = "//span[text()='Delete']";
    private final String TEST_CASE_TITLE = "//div[text()='%s']";

    @Override
    public TestCasePage isPageOpened() {
        $x(TEST_CASE_PAGE).shouldBe(visible);
        return this;
    }

    public TestCasePage createNewTestCase() {
        $(CREATE_CASE_BUTTON).shouldBe(visible, Duration.ofSeconds(40));
        $(CREATE_CASE_BUTTON).click();
        return this;
    }

    public TestCasePage fillTestCaseName(TestCase testCase) {
        $(TEST_CASE_NAME).sendKeys(testCase.getTitle());
        return this;
    }

    public TestCasePage editTestCaseName(TestCase testCase) {
        $(TEST_CASE_NAME).clear();
        $(TEST_CASE_NAME).sendKeys(testCase.getTitle());
        return this;
    }

    public TestCasePage fillRequiredField(TestCase testCase) {
        new QaseSelect("Status").selectOption(testCase.getStatus());
        new QaseSelect("Severity").selectOption(testCase.getSeverity());
        new QaseSelect("Priority").selectOption(testCase.getPriority());
        new QaseSelect("Type").selectOption(testCase.getType());
        new QaseSelect("Layer").selectOption(testCase.getLayer());
        new QaseSelect("Is flaky").selectOption(testCase.getIsFlaky());
        new QaseSelect("Behavior").selectOption(testCase.getBehavior());
        new QaseSelect("Automation status").selectOption(testCase.getAutomationStatus());
        $(SAVE_CASE_BUTTON).click();
        return this;
    }

    public TestCasePage openTestCase(String testCaseName) {
        $x(String.format(TEST_CASE_LINK, testCaseName)).shouldBe(visible);
        return this;
    }

    public void editTestCase() {
        $x(EDIT_BUTTON).click();
        $x(EDIT_TEST_CASE_PAGE).shouldBe(Condition.visible);
    }

    public void deleteTestCase() {
        $x(DELETE_BUTTON).click();
        $x(DELETE_TEST_CASE).click();
    }

    public boolean isTestCaseExist(String testCaseName) {
        return $x(String.format(TEST_CASE_TITLE, testCaseName)).isDisplayed();
    }

    public String getTestCaseName() {
        return $(TEST_CASE_NAME).getValue();
    }

    public TestCasePage validateDetails(TestCase testCase) {
        validateFieldValue("Status", testCase.getStatus());
        validateFieldValue("Severity", testCase.getSeverity());
        validateFieldValue("Priority", testCase.getPriority());
        validateFieldValue("Type", testCase.getType());
        validateFieldValue("Layer", testCase.getLayer());
        validateFieldValue("Is flaky", testCase.getIsFlaky());
        validateFieldValue("Behavior", testCase.getBehavior());
        validateFieldValue("Automation status", testCase.getAutomationStatus());
        return this;
    }
}
