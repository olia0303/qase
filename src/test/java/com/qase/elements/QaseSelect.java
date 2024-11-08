package com.qase.elements;

import com.codeborne.selenide.Condition;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;

public class QaseSelect extends ElementImpl {
    private static final String SELECT_ELEMENT_XPATH = "//label[text()='%s']/../div";
    private static final String SELECT_OPTION = "//div[@id='modals']//div[text()='%s']";

    public String label;

    public QaseSelect(String label) {
        find(label, By.xpath(String.format(SELECT_ELEMENT_XPATH, label)));
        this.label = label;
    }

    public void selectOption(String option) {
        if (StringUtils.isNotEmpty(option)) {
            element.shouldBe(Condition.visible).click();
            $x(String.format(SELECT_OPTION, option)).click();
        }
    }
}
