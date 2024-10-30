package com.qase.pageobjects;

import com.qase.model.Suite;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SuitesPage extends BasePage {

    private final String CREATE_NEW_SUITE_BUTTON = "//span[contains(text(),'Create new suite')]";
    private final String SUITE_NAME = "#title";
    private final String SUITE_DESCRIPTION = "#description";
    private final String SUITE_PRECONDITIONS = "#preconditions";

    @Override
    public SuitesPage isPageOpened() {
        $(By.xpath("//span[contains(text(),'Suites')]"))
                .shouldBe(visible);
        return this;
    }

    public SuitesPage createNewSuite(Suite suite) {
        loadPage();
        $(By.xpath(CREATE_NEW_SUITE_BUTTON)).click();
        $(SUITE_NAME).sendKeys(suite.getSuiteName());
        $(SUITE_DESCRIPTION).sendKeys(suite.getDescription());
        $(SUITE_PRECONDITIONS).sendKeys(suite.getPreconditions());
        submit();
        return this;
    }

    public SuitesPage openEditSuitePage() {
        $(By.xpath("//button[@aria-label='Edit suite']")).click();
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
        $(By.xpath("//div[contains(text(),'Looks" +
                " like you don’t have any suites and cases yet')]")).shouldBe(visible, Duration.ofSeconds(30));
    }
}
