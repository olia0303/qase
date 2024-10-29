package com.qase.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    @Test(description = "Check login with valid username and valid password")
    public void logInSuccessful() {
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened();
    }

    @Test(description = "Check login with empty username and valid password")
    public void emptyLogin() {
        loginPage.openPage()
                .logIn("", testData.PASS);
        assertEquals(loginPage.getErrorMessage(), "This field is required");
    }

    @Test(description = "Check login with valid username and empty password")
    public void emptyPassword() {
        loginPage.openPage()
                .logIn(testData.USER, "");
        assertEquals(loginPage.getErrorMessage(), "This field is required");
    }

    @Test(description = "Check login with valid username and invalid password")
    public void invalidPasswordLogin() {
        loginPage.openPage()
                .logIn(testData.USER, "123456");
        assertEquals(loginPage.getAlertMessage(), "Security notice: The password entered " +
                "has been found in a public data leak. " + "Please reset your password to " +
                "ensure the safety of your account");
    }

    @Test(description = "Check login with invalid username and invalid password")
    public void invalidCredentialsLogin() {
        loginPage.openPage()
                .logIn("asdf@mail.ru", "123456");
        assertEquals(loginPage.getAlertMessage(), "Security notice: The password entered " +
                "has been found in a public data leak. " + "Please reset your password to " +
                "ensure the safety of your account");
    }
}