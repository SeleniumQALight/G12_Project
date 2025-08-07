package org.pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreateNewPostPage;
import org.pages.MyProfilePage;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy (xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy (xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;


    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }
    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);

    }

    @Step
    public void checkButtonSignOutVisible() {
        checkIsElementDisplayed(buttonSignOut);
    }

    @Step
    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    @Step
    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    @Step
    public void checkButtonSignOutIsNotVisible() {
        checkIsElementNotDisplayed(buttonSignOut);
    }
}
