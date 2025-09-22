package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage{
    @FindBy(xpath = "//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//p[text() = 'Is this post unique? : yes']")
    private WebElement messageUniquePost;

    private String messageUniquePostParams = "//p[text() = 'Is this post unique? : %s']";

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = "//a[@data-original-title='Edit']")
    private WebElement buttonEditPost;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {

        return "/post/[a-zA-Z0-9]*";
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        // TODO check some unique element on the page
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

    public PostPage checkIsPostUnique() {
checkIsElementDisplayed(messageUniquePost);
return this;
    }

    public PostPage checkIsPostUnique(String isUnique) {
        checkIsElementDisplayed(webDriver.findElement(By.xpath(String.format(messageUniquePostParams, isUnique))));
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "Delete post button");
        return new MyProfilePage(webDriver);
    }

    public CreateNewPostPage clickOnEditButton() {
        clickOnElement(buttonEditPost, "Edit post button");
        return new CreateNewPostPage(webDriver);
    }

}
