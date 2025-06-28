package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreateNewPost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkButtonSignOutVisible() {
        checkIsElementDisplayed(buttonSignOut);
    }

    public HomePage checkIsRedirectToHomePage() {
        //TODO check URL
        checkButtonSignOutVisible();
        return this;
    }

    public CreateNewPostPage clickOnButtonCreateNewPost() {
        clickOnElement(buttonCreateNewPost);
        return new CreateNewPostPage(webDriver);
    }
}
