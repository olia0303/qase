package com.qase.pageobjects;

import com.codeborne.selenide.Condition;
import com.qase.model.Project;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectSettingPage extends BasePage {

    private final String PROJECT_SETTING_PAGE = "//h1[contains(text(),'Project settings')]";

    @Override
    public ProjectSettingPage isPageOpened() {
        $x(PROJECT_SETTING_PAGE).shouldBe(Condition.visible);
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

    public ProjectSettingPage updateProjectSettings(Project project) {
        $(PROJECT_NAME).clear();
        $(PROJECT_NAME).sendKeys(project.getName());
        $(PROJECT_CODE).clear();
        $(PROJECT_CODE).sendKeys(project.getCode());
        $(PROJECT_DESCRIPTION).clear();
        $(PROJECT_DESCRIPTION).sendKeys(project.getDescription());
        submit();
        return this;
    }
}
