package com.qase.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.qase.model.Project;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.qase.other.Urls.PROJECTS_PAGE;

public class ProjectsPage extends BasePage {

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
        $(By.id("createButton")).click();
        $(By.id("project-name")).sendKeys(project.getName());
        $(By.id("project-code")).clear();
        $(By.id("project-code")).sendKeys(project.getCode());
        $(By.id("description-area")).sendKeys(project.getDescription());
        buttonPress();
        return this;
    }

    public ProjectsPage openProjectByName(String projectName) {
        SelenideElement el;
        el = $(By.xpath(String.format("//a[contains(text(),\"%s\")]/../../../..//span", projectName)));
        el.click();
        return this;
    }

    public ProjectsPage openProjectSettings() {
        $(By.xpath("//a[contains(text(),'Settings')]")).click();
        return this;
    }
}