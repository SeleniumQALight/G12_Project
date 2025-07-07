package org.pages;


import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage {
//    Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        //TODO check URL
        getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        return this;

    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public LoginPage verifyButtonSignOutIsNotVisible() {
        checkIsElementIsNotDisplayed(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public LoginPage verifyButtonCreatePostIsVisible() {
        checkIsElementDisplayed(buttonCreatePost);
        return new LoginPage(webDriver);
    }
}
