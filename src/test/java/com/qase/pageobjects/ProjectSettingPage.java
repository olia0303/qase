package com.qase.pageobjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProjectSettingPage extends BasePage {

    @Override
    public ProjectSettingPage isPageOpened() {
        $(By.xpath("//h1[contains(text(),'Project settings')]")).shouldBe(Condition.visible);
        return this;
    }

    public String getProjectName() {
        return $(PROJECT_NAME).getValue();
    }

    public String getProjectCode() {
        return $(PROJECT_CODE).getValue();
    }

    public String getProjectDescription() {
        return $(PROJECT_DESCRIPTION).getText();
    }
}
