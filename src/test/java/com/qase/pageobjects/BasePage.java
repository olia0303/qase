package com.qase.pageobjects;

import com.qase.api.APIUtilsExtended;
import com.qase.api.RestApiUtils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertEquals;

public abstract class BasePage {
    abstract protected BasePage isPageOpened();

    protected final String PROJECT_NAME = "#project-name";
    protected final String PROJECT_CODE = "#project-code";
    protected final String PROJECT_DESCRIPTION = "#description-area";
    protected final String SUBMIT_BUTTON = "[type=submit]";
    public static final String FIELD_XPATH = "//label[text()=\"%s\"]/../div";

    public void submit() {
        $(SUBMIT_BUTTON).click();
    }

    public String getDisplayValue(String fieldTitle) {
        return $x(String.format(FIELD_XPATH, fieldTitle)).getText();
    }

    public void validateFieldValue(String fieldName, String value) {
        assertEquals(getDisplayValue(fieldName), value);
    }

    RestApiUtils restApiUtils = new RestApiUtils();
    APIUtilsExtended apiUtilsExtended = new APIUtilsExtended(restApiUtils);
}