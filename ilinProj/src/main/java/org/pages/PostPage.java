package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//p[text() = 'Is this post unique? : yes']")
    private WebElement messageUniquePost;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = "//a[@class='text-primary mr-2']")
    private WebElement buttonEditPost;


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

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        //TODO check some unique elements on the page
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

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "Delete post button");
        return new MyProfilePage(webDriver);
    }

    public PostPage checkIsPostUnique() {
        checkIsElementDisplayed(messageUniquePost);
        return this;
    }

    public EditPostPage clickOnEditPostButton() {
        clickOnElement(buttonEditPost, "Edit post button");
        return new EditPostPage(webDriver);
    }
}
