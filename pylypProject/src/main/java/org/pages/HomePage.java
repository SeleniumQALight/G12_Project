package org.pages;


import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;

public class HomePage extends ParentPage {
//    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;
  
import org.pages.elements.HeaderForLoggedUserElement;

import javax.net.ssl.HostnameVerifier;

public class HomePage extends ParentPage{
Logger logger = Logger.getLogger(HomePage.class);

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

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

    public HomePage checkIsRedirectedToHomePage() {
checkUrl();
getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        return this;
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(getHeaderForLoggedUserElement().isButtonSignOutVisible()) {
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

    // перевірка, що кнопка Sign Out не показується
    public HomePage checkIsButtonSignOutNotVisible() {
        Assert.assertFalse("Sign Out button should not be visible", isElementDisplayed(buttonSignOut));
        return this;
    }

    // перевірка, що інпут логіна зник (не існує в DOM)
    public HomePage checkIsLoginInputNotVisible() {
        Assert.assertFalse("Login input should not be visible on HomePage",
                isElementDisplayed(inputLogin));
        return this;
    }

    // перевірка, що інпут пароля зник (не існує в DOM)
    public HomePage checkIsPasswordInputNotVisible() {
        Assert.assertFalse("Password input should not be visible on HomePage",
                isElementDisplayed(inputPassword));
        return this;
    }

    // перевірка, що після логіну є кнопка Create Post
    public HomePage checkIsButtonCreatePostVisible() {
        checkIsElementDisplayed(buttonCreatePost);
        return this;
    }
}