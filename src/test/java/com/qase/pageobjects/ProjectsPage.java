package com.qase.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.qase.model.Project;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.qase.other.Urls.PROJECTS_PAGE;

public class ProjectsPage extends BasePage {

    private final String FIELD_XPATH = "//a[contains(text(),\"%s\")]/following::td[5]//span";
    private final String CREATE_BUTTON = "#createButton";

    @Override
    public ProjectsPage isPageOpened() {
        $(By.xpath(" //h1[contains(text(),'Projects')]")).shouldBe(Condition.visible);
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
        el = $(By.xpath(String.format(FIELD_XPATH, projectName)));
        el.click();
        return this;
    }

    public ProjectsPage openProjectSettings() {
        $(By.xpath("//a[contains(text(),'Settings')]")).click();
        return this;
    }

    public ProjectsPage removeProject() {
        $(By.xpath("//button[contains(text(),'Remove')]")).click();
        $(By.xpath("//span[contains(text(),'Delete project')]")).click();
        return this;
    }

    public boolean isProjectExist(String projectName) {
        return $(By.xpath(String.format(FIELD_XPATH, projectName))).isDisplayed();
    }
}