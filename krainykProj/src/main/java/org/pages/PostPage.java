package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage {
    final String uniquePostTextMessage = "Is this post unique? : ";

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//p[contains(text(), 'Is this post unique?')]")
    private WebElement uniquePostText;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        // TODO check URL
        // TODO check some unique element on the page
        return this;
    }


    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementDisplayed(successMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedText) {
        checkTextInElement(successMessage, expectedText);
        return this;
    }

    public PostPage checkUniquenessOfPost(String expectedValue) {
        if (expectedValue.equalsIgnoreCase("check")) {
            checkTextInElement(uniquePostText, uniquePostTextMessage + "yes");
        }
        else if (expectedValue.equalsIgnoreCase("uncheck")) {
            checkTextInElement(uniquePostText, uniquePostTextMessage + "no");
        }
        return this;
    }

    public MyProfilePage clickOnDeletePostButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }
}
