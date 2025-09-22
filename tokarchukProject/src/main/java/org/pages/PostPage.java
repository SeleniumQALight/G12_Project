package org.pages;

import org.enums.CheckboxState;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//p[contains(text(), 'Is this post unique?')]")
    private WebElement uniquePostInfo;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = "//a[@data-original-title='Edit']")
    private WebElement buttonEditPost;

    @FindBy(xpath = "//div/h2")
    private WebElement postTitle;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/post/[a-zA-Z0-9]*";
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectedToPostPage() {
        //TODO check URL
        //TODO check some unique element on the page
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

    public PostPage verifyUniquePostState(CheckboxState expectedState) {
        String expectedText = (expectedState == CheckboxState.CHECK)
                ? "Is this post unique? : yes"
                : "Is this post unique? : no";
        checkTextInElement(uniquePostInfo, expectedText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "Delete Post Button");
        return new MyProfilePage(webDriver);
    }

    public EditPostPage clickOnEditPostButton() {
        clickOnElement(buttonEditPost, "Edit Post Button");
        return new EditPostPage(webDriver);
    }

    public PostPage checkPostTitle(String expectedTitle) {
        checkTextInElement(postTitle, expectedTitle);
        return this;
    }

}
