package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage{
    @FindBy(name = "title") // @FindBy(xpath = "//*[@name='title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = "//a[contains(text(), 'Back to post permalink')]")
    private WebElement linkBackToPostPermalink;

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        return this;
    }

    public EditPostPage clickOnSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public EditPostPage editTextIntoInputTitleOnEditPage(String title) {
        clearAndEnterTextToElement(inputTitle, title);
        return this;
    }

    public EditPostPage checkIsSuccessPostEditedMessageDisplayed() {
        checkIsElementDisplayed(successMessage, "Success message");
        return this;
    }

    public EditPostPage checkTextInSuccessPostEditedMessage(String expectedText) {
        checkTextInElement(successMessage, expectedText);
        return this;
    }

    public PostPage clickOnLinkBackToPostPermalink() {
        clickOnElement(linkBackToPostPermalink);
        return new PostPage(webDriver);
    }
}