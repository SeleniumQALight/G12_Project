package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreateNewPostPage;
import org.pages.MyProfilePage;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//a[contains(@class,'header-search-icon')]")
    private WebElement iconSearch;

    @FindBy(xpath = "//span[@data-original-title='Chat']")
    private WebElement iconChat;

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isMyProfileIconVisible() {
        return isElementDisplayed(buttonMyProfile);
    }

    public void checkButtonSignOutVisible() {
        checkIsElementDisplayed(buttonSignOut);
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public boolean isCreatePostButtonVisible() {
        return isElementDisplayed(buttonCreatePost);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public void checkSearchIconIsVisible() {
        checkIsElementDisplayed(iconSearch);
    }

    public void checkChatIconIsVisible() {
        checkIsElementDisplayed(iconChat);
    }

    public void clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
    }
}
