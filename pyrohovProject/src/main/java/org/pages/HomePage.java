package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

//    @FindBy(xpath = "//button[text()='Sign Out']")
//    private WebElement buttonSignOut;
//
//    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
//    private WebElement buttonCreatePost;

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


    public HomePage chekIsRedirectToHomePage() {
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
            loginPage.enterTextIntiInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            chekIsRedirectToHomePage();
            logger.info("User was not logged in");
        }

        return this;
    }
//    public void checkButtonSignOutVisible() {
//        checkIsElementDisplayed(buttonSignOut);
//    }
//
//    public CreateNewPostPage clickOnButtonCreatePost() {
//        clickOnElement(buttonCreatePost);
//        return new CreateNewPostPage(webDriver);
//    }
}
