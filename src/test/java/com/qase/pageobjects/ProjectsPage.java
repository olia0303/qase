package com.qase.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.qase.model.Project;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.qase.other.Urls.BASE_URL;

public class ProjectsPage extends BasePage {

    private final String FIELD_XPATH = "//a[contains(text(),\"%s\")]/following::td[5]//span";
    private final String CREATE_BUTTON = "//span[contains(text(),'Create new project')]";
    private final String PROJECTS_PAGE = "//h1[contains(text(),'Projects')]";
    private final String SETTINGS = "//div[@data-testid='settings']";
    private final String REMOVE_BUTTON = "//div[@data-testid='remove']";
    private final String DELETE_PROJECT = "//span[contains(text(),'Delete project')]";
    private final String PROJECTS_PAGE_URL = BASE_URL + "/projects";
    private final String PROJECT_REPOSITORY_URL = BASE_URL + "/project/";

    @Override
    public ProjectsPage isPageOpened() {
        $x(PROJECTS_PAGE).shouldBe(Condition.visible, Duration.ofSeconds(50));
        return this;
    }

    @Step("Open Project page")
    public ProjectsPage openPage() {
        open(PROJECTS_PAGE_URL);
        return this;
    }

    @Step("Create new project")
    public ProjectsPage createNewProject(Project project) {
        isPageOpened();
        $x(CREATE_BUTTON).click();
        $(PROJECT_NAME).sendKeys(project.getName());
        $(PROJECT_CODE).clear();
        $(PROJECT_CODE).sendKeys(project.getCode());
        $(PROJECT_DESCRIPTION).sendKeys(project.getDescription());
        submit();
        return this;
    }

    @Step("Open project by name: {projectName}")
    public ProjectsPage openProjectByName(String projectName) {
        SelenideElement el;
        el = $x(String.format(FIELD_XPATH, projectName));
        el.click();
        return this;
    }

    @Step("Open project settings")
    public ProjectsPage openProjectSettings() {
        $x(SETTINGS).click();
        return this;
    }

    @Step("Delete project")
    public ProjectsPage removeProject() {
        $x(REMOVE_BUTTON).click();
        $x(DELETE_PROJECT).click();
        return this;
    }

    public boolean isProjectExist(String projectName) {
        return $x(String.format(FIELD_XPATH, projectName)).isDisplayed();
    }

    @Step("Open project repository: {code}")
    public ProjectsPage openProjectRepository(String code) {
        open(PROJECT_REPOSITORY_URL + code);
        return this;
    }
}