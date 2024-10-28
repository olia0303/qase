package com.qase.pageobjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.qase.other.Urls.LOGIN_PAGE;

public class LoginPage extends BasePage {
    private final By ERROR_MESSAGE = By.cssSelector("small.f75Cb_");
    private final By ALERT_MESSAGE = By.xpath("//div[@role='alert']//span/span");

    public LoginPage openPage() {
        open(LOGIN_PAGE);
        return this;
    }

    public LoginPage logIn(String username, String password) {
        isPageOpened();
        $("[name=email]").sendKeys(username);
        $("[name=password]").sendKeys(password);
        buttonPress();
        return this;
    }

    public String getErrorMessage() {
        String fieldErrorMessage = $(ERROR_MESSAGE).getText();
        return fieldErrorMessage;
    }

    public String getAlertMessage() {
        String alertMessage = $(ALERT_MESSAGE).getText();
        return alertMessage;
    }

    @Override
    public LoginPage isPageOpened() {
        $(By.xpath("//h1[contains(text(),'Log in to your account')]")).shouldBe(Condition.visible);
        return this;
    }
}