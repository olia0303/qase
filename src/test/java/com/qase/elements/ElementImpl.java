package com.qase.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class ElementImpl {
    protected SelenideElement element;
    protected String label;

    public void find(String label, By locator) {
        this.label = label;
        element = $(locator);
        element.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }
}
