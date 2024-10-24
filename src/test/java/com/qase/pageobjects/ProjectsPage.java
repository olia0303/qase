package com.qase.pageobjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProjectsPage extends BasePage {
    @Override
    public ProjectsPage isPageOpened() {
        $(By.xpath(" //h1[contains(text(),'Projects')]")).shouldBe(Condition.visible);
        return this;
    }
}