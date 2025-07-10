package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;
    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement deletePostButton;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/post/[a-zA-z0-9]*";
    }

    public HeaderForLoggedUserElement getheaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementDisplayed(successMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        checkTextInElement(successMessage, expectedMessageText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(deletePostButton, "Delete button");
        return new MyProfilePage(webDriver);
    }
}
