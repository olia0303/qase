package com.qase.pageobjects;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {
    abstract protected BasePage isPageOpened();

    public void buttonPress() {
        $("[type=submit]").click();
    }
}