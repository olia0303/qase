package com.qase.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.qase.api.ProjectAdapter;
import com.qase.model.Project;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.qase.other.Urls.PROJECTS_PAGE_URL;
import static com.qase.other.Urls.PROJECT_REPOSITORY_URL;

public class ProjectsPage extends BasePage {

    private final String FIELD_XPATH = "//a[contains(text(),\"%s\")]/following::td[5]//span";
    private final String CREATE_BUTTON = "#createButton";
    private final String PROJECTS_PAGE = "//h1[contains(text(),'Projects')]";
    private final String SETTINGS = "//div[@data-testid='settings']";
    private final String REMOVE_BUTTON = "//div[@data-testid='remove']";
    private final String DELETE_PROJECT = "//span[contains(text(),'Delete project')]";

    @Override
    public ProjectsPage isPageOpened() {
        $x(PROJECTS_PAGE).shouldBe(Condition.visible, Duration.ofSeconds(50));
        return this;
    }

    public ProjectsPage openPage() {
        open(PROJECTS_PAGE_URL);
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

    public ProjectsPage openProjectRepository(String code) {
        open(PROJECT_REPOSITORY_URL + code);
        return this;
    }

    public ProjectsPage createProjectViaApi(Project project) {
        ProjectAdapter projectAdapter = new ProjectAdapter(apiUtilsExtended);
        projectAdapter.post(project);
        return this;
    }

    public ProjectsPage deleteProjectViaApi(String code) {
        ProjectAdapter projectAdapter = new ProjectAdapter(apiUtilsExtended);
        projectAdapter.delete(code);
        return this;
    }
}