package com.qase.pageobjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.qase.other.Urls.LOGIN_PAGE;

public class LoginPage extends BasePage {
    private final By ERROR_MESSAGE = By.cssSelector("small.f75Cb_");
    private final String ALERT_MESSAGE = "//div[@role='alert']//span/span";
    private final String LOGIN_PAGE_XPATH = "//h1[contains(text(),'Log in to your account')]";
    private final String USER_EMAIL = "[name=email]";
    private final String USER_PASS = "[name=password]";

    public LoginPage openPage() {
        open(LOGIN_PAGE);
        return this;
    }

    public LoginPage logIn(String username, String password) {
        isPageOpened();
        $(USER_EMAIL).sendKeys(username);
        $(USER_PASS).sendKeys(password);
        submit();
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
        $x(LOGIN_PAGE_XPATH).shouldBe(Condition.visible);
        return this;
    }
}