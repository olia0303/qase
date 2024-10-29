package com.qase.pageobjects;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {
    abstract protected BasePage isPageOpened();

    protected final String PROJECT_NAME = "#project-name";
    protected final String PROJECT_CODE = "#project-code";
    protected final String PROJECT_DESCRIPTION = "#description-area";

    public void submit() {
        $("[type=submit]").click();
    }
}