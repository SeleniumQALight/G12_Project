package org.pages;

import org.apache.log4j.Logger;
import data.TestData;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage {

    Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        checkUrl();
        getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        return this;
    }

    public HomePage openHomePageAndLoginNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (getHeaderForLoggedUserElement().isButtonSighnOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickLoginButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }
}