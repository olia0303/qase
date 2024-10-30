package com.qase.pageobjects;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {
    abstract protected BasePage isPageOpened();

    protected final String PROJECT_NAME = "#project-name";
    protected final String PROJECT_CODE = "#project-code";
    protected final String PROJECT_DESCRIPTION = "#description-area";
    protected final String SUBMIT_BUTTON = "[type=submit]";

    public void submit() {
        $(SUBMIT_BUTTON).click();
    }
}