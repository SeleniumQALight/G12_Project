package org.pages;

import org.apache.log4j.Logger;
import data.TestData;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForLoggedUserElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    Logger logger = Logger.getLogger(HomePage.class);
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreateNewPost;

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

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (getHeaderForLoggedUserElement().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextInInputLogin(TestData.VALID_LOGIN_UI)
                    .enterTextInInputPassword(TestData.VALID_PASSWORD_UI)
                    .clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }
}
