package com.qase.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.qase.model.Project;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class ProjectsPage extends BasePage {

    private final String FIELD_XPATH = "//a[contains(text(),\"%s\")]/following::td[5]//span";
    private final String CREATE_BUTTON = "#createButton";
    private final String PROJECTS_PAGE = "//h1[contains(text(),'Projects')]";
    private final String SETTINGS = "//a[contains(text(),'Settings')]";
    private final String REMOVE_BUTTON = "//button[contains(text(),'Remove')]";
    private final String DELETE_PROJECT = "//span[contains(text(),'Delete project')]";

    @Override
    public ProjectsPage isPageOpened() {
        $x(PROJECTS_PAGE).shouldBe(Condition.visible, Duration.ofSeconds(30));
        return this;
    }

    public ProjectsPage openPage() {
        open(PROJECTS_PAGE);
        return this;
    }

    public ProjectsPage createNewProject(Project project) {
        isPageOpened();
        $(CREATE_BUTTON).click();
        $(PROJECT_NAME).sendKeys(project.getName());
        $(PROJECT_CODE).clear();
        $(PROJECT_CODE).sendKeys(project.getCode());
        $(PROJECT_DESCRIPTION).sendKeys(project.getDescription());
        submit();
        return this;
    }

    public ProjectsPage openProjectByName(String projectName) {
        SelenideElement el;
        el = $x(String.format(FIELD_XPATH, projectName));
        el.click();
        return this;
    }

    public ProjectsPage openProjectSettings() {
        $x(SETTINGS).click();
        return this;
    }

    public ProjectsPage removeProject() {
        $x(REMOVE_BUTTON).click();
        $x(DELETE_PROJECT).click();
        return this;
    }

    public boolean isProjectExist(String projectName) {
        return $x(String.format(FIELD_XPATH, projectName)).isDisplayed();
    }
}