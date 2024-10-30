package com.qase.pageobjects;

import com.qase.model.Suite;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SuitesPage extends BasePage {

    private final String CREATE_NEW_SUITE_BUTTON = "//span[contains(text(),'Create new suite')]";
    private final String SUITE_NAME = "#title";
    private final String SUITE_DESCRIPTION = "#description";
    private final String SUITE_PRECONDITIONS = "#preconditions";
    private final String SUITES_PAGE = "//span[contains(text(),'Suites')]";
    private final String EDIT_SUITE_BUTTON = "//button[@aria-label='Edit suite']";
    private final String MESSAGE = "//div[contains(text(),'Looks like you don’t have any suites and cases yet.')]";

    @Override
    public SuitesPage isPageOpened() {
        $x(SUITES_PAGE).shouldBe(visible);
        return this;
    }

    public SuitesPage createNewSuite(Suite suite) {
        loadPage();
        $x(CREATE_NEW_SUITE_BUTTON).click();
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

    public String getSuiteName() {
        return $(SUITE_NAME).getValue();
    }

    public String getSuiteDescription() {
        return $(SUITE_DESCRIPTION).getValue();
    }

    public String getSuitePreconditions() {
        return $(SUITE_PRECONDITIONS).getValue();
    }

    public void loadPage() {
        $x(MESSAGE).shouldBe(visible, Duration.ofSeconds(30));
    }
}
