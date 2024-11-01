package com.qase.pageobjects;

import com.qase.model.Suite;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SuitesPage extends BasePage {

    private final String CREATE_SUITE = "#create-suite-button";
    private final String SUITE_NAME = "#title";
    private final String SUITE_DESCRIPTION = "#description";
    private final String UPDATE_SUITE_DESCRIPTION = "//input[@id='description']/preceding-sibling::div" +
            "//div[@class='toastui-editor ww-mode']//p";
    private final String UPDATE_SUITE_PRECONDITIONS = "//input[@id='preconditions']/preceding-sibling::div" +
            "//div[@class='toastui-editor ww-mode']//p";
    private final String SUITE_PRECONDITIONS = "#preconditions";
    private final String SUITES_PAGE = "//span[contains(text(),'Suites')]";
    private final String EDIT_SUITE_BUTTON = "//button[@aria-label='Edit suite']";
    private final String DELETE_SUITE_BUTTON = "//button[@aria-label='Delete suite']";
    private final String DELETE_BUTTON = "//span[contains(text(),'Delete')]";
    private final String SUITE_TITLE = "//h3[contains(text(),\"%s\")]";
    private final String SAVE_BUTTON = "//span[contains(text(),'Save')]";

    @Override
    public SuitesPage isPageOpened() {
        $x(SUITES_PAGE).shouldBe(visible);
        return this;
    }

    public SuitesPage createNewSuite(Suite suite) {
        $(CREATE_SUITE).shouldBe(visible, Duration.ofSeconds(40));
        $(CREATE_SUITE).click();
        $(SUITE_NAME).sendKeys(suite.getSuiteName());
        $(SUITE_DESCRIPTION).sendKeys(suite.getDescription());
        $(SUITE_PRECONDITIONS).sendKeys(suite.getPreconditions());
        submit();
        return this;
    }

    public SuitesPage openEditSuitePage() {
        $x(EDIT_SUITE_BUTTON).click();
        return this;
    }

    public SuitesPage deleteSuite() {
        $x(DELETE_SUITE_BUTTON).click();
        $x(DELETE_BUTTON).click();
        return this;
    }

    public String getSuiteName() {
        return $(SUITE_NAME).getValue();
    }

    public String getSuiteDescription() {
        return $(SUITE_DESCRIPTION).getValue();
    }

    public String getSuitePreconditions() {
        return $(SUITE_PRECONDITIONS).getValue();
    }

    public void save() {
        $x(SAVE_BUTTON).click();
    }

    public SuitesPage updateSuite(Suite suite) {
        $(SUITE_NAME).clear();
        $(SUITE_NAME).sendKeys(suite.getSuiteName());
        $x(UPDATE_SUITE_DESCRIPTION).clear();
        $x(UPDATE_SUITE_DESCRIPTION).sendKeys(suite.getDescription());
        $x(UPDATE_SUITE_PRECONDITIONS).clear();
        $x(UPDATE_SUITE_PRECONDITIONS).sendKeys(suite.getPreconditions());
        save();
        return this;
    }

    public boolean isSuiteExist(String suiteName) {
        return $x(String.format(SUITE_TITLE, suiteName)).isDisplayed();
    }
}
