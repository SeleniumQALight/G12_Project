package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.*;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//a[@href='#']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@data-original-title='Chat']")
    private WebElement buttonChatCall;

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public void checkButtonSignOutVisible() {
//        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        checkIsElementDisplayed(buttonSignOut);
    }


    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public LoginPage checkButtonSignOutIsNotVisible() {
        checkIsElementNotDisplayed(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public LoginPage checkButtonCreatePostVisible() {
        checkIsElementDisplayed(buttonCreatePost);
        return new LoginPage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public void clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
    }

    public HeaderForLoggedUserElement checkIsElementsInHeaderForLoggedUserVisible() {
        checkButtonSearchIsVisible()
                .checkButtonChatCallIsVisible()
                .checkButtonMyProfileIsVisible()
                .checkButtonCreatePostVisible();
        checkButtonSignOutVisible();
        return this;
    }

    public HeaderForLoggedUserElement checkButtonSearchIsVisible() {
        checkIsElementDisplayed(buttonSearch);
        return this;
    }

    public HeaderForLoggedUserElement checkButtonChatCallIsVisible() {
        checkIsElementDisplayed(buttonChatCall);

        return this;
    }

    public HeaderForLoggedUserElement checkButtonMyProfileIsVisible() {
        checkIsElementDisplayed(buttonMyProfile);
        return this;
    }

    public LoginPage checkElementsInHeaderForLoggedUserIsNotVisible() {
        checkButtonSearchIsNotVisible()
                .checkButtonChatCallIsNotVisible()
                .checkButtonMyProfileIsNotVisible()
                .checkButtonCreatePostIsNotVisible();
        checkButtonSignOutIsNotVisible();
        return new LoginPage(webDriver);
    }

    private HeaderForLoggedUserElement checkButtonCreatePostIsNotVisible() {
        checkIsElementNotDisplayed(buttonCreatePost);
        return this;
    }

    private HeaderForLoggedUserElement checkButtonMyProfileIsNotVisible() {
        checkIsElementNotDisplayed(buttonMyProfile);
        return this;
    }

    private HeaderForLoggedUserElement checkButtonChatCallIsNotVisible() {
        checkIsElementNotDisplayed(buttonChatCall);
        return this;
    }

    private HeaderForLoggedUserElement checkButtonSearchIsNotVisible() {
        checkIsElementNotDisplayed(buttonSearch);
        return this;
    }

    public SearchElements clickOnButtonSearch() {
        clickOnElement(buttonSearch);
        return new SearchElements(webDriver);
    }

}


