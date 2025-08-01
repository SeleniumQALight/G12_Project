package org.pages;


import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage{
    Logger logger = Logger.getLogger(HomePage.class);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/";
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
}

    public HomePage checkIsRedirectedToHomePage() {
        checkURL();
        getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        return this;
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (getHeaderForLoggedUserElement().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
           loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI)
                    .enterTestIntoPassword(TestData.VALID_PASSWORD_UI)
                    .clickOnButtonSignIn();
                    checkIsRedirectedToHomePage();
            logger.info("User logged in successfully");
        }
        return this;
    }

    public HomePage checkSignOutButtonIsNotVisible() {
        getHeaderForLoggedUserElement().checkButtonSignOutIsNotVisible();
        return this;
    }
}
