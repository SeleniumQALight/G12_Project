package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelatedURL() {
        return "/";
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public HomePage checkIsRedirectedToHomePage() {
        //TODO check URL
        getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        checkURL();
        getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        return this;
    }
    public HomePage checkButtonCreatePostVisible() {
        getHeaderForLoggedUserElement().checkButtonCreatePostVisible();
        return this;
    }

    public HomePage openHomePageAndLoginIfNeeds() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (getHeaderForLoggedUserElement().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI)
                    .enterTextIntoPassword(TestData.VALID_PASSWORD_UI)
                    .clickOnButtonSignIn();
            checkIsRedirectedToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }

}
