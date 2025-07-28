package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreateNewPostPage;
import org.pages.LoginPage;
import org.pages.MyProfilePage;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreateNewPost;

    @FindBy(xpath = "//a[@data-original-title='Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@class='text-white mr-2 header-chat-icon']")
    private WebElement buttonChat;


    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public void checkButtonSignOutVisible() {
        //  Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        checkIsElementDisplayed(buttonSignOut);
    }

    public void checkButtonSignOutNotVisible() {
        checkIsElementNotDisplayed(buttonSignOut);
    }

    public CreateNewPostPage clickOnButtonCreateNewPost() {
        clickOnElement(buttonCreateNewPost);
        return new CreateNewPostPage(webDriver);
    }


    public void checkButtonCreatePostVisible() {

        checkIsElementDisplayed(buttonCreateNewPost);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public HeaderForLoggedUserElement checkButtonSearchVisible() {
        checkIsElementDisplayed(buttonSearch);
        return this;
    }

    public HeaderForLoggedUserElement checkButtonChatVisible() {
        checkIsElementDisplayed(buttonChat);
        return this;
    }

    public HeaderForLoggedUserElement checkButtonMyProfileVisible() {
        checkIsElementDisplayed(buttonMyProfile);
        return this;
    }
    public HeaderForLoggedUserElement checkButtonSearchNotVisible() {
        checkIsElementNotDisplayed(buttonSearch);
        return this;
    }

    public HeaderForLoggedUserElement checkButtonChatNotVisible() {
        checkIsElementNotDisplayed(buttonChat);
        return this;
    }

    public HeaderForLoggedUserElement checkButtonMyProfileNotVisible() {
        checkIsElementNotDisplayed(buttonMyProfile);
        return this;
    }

    public HeaderForLoggedUserElement checkButtonCreatePostNotVisible() {
        checkIsElementNotDisplayed(buttonCreateNewPost);
        return this;
    }

    public HeaderForLoggedUserElement checkElementsInHeaderForLoggedUserVisible() {
        checkButtonSearchVisible()
                .checkButtonChatVisible()
                .checkButtonMyProfileVisible()
                .checkButtonCreatePostVisible();
        checkButtonSignOutVisible();
        return this;
    }

    public void checkElementsInHeaderForLoggedUserNotVisible() {
        checkButtonSearchNotVisible()
                .checkButtonChatNotVisible()
                .checkButtonMyProfileNotVisible()
                .checkButtonCreatePostNotVisible()
                .checkButtonSignOutNotVisible();
    }
}

