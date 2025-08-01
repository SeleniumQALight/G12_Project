package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = ".//a[contains(@class,'edit-post')]")
    private WebElement buttonEdit;

    @FindBy(xpath = "//p[contains(text(), 'Is this post unique?')]")
    private WebElement uniquePostText;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelatedURL() {
        return "/post/[a-zA-Z0-9]*";
    }

    public HeaderForLoggedUserElement getheaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        checkURLWithPattern();
        // TODO check same unique element on the page
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

    public PostPage checkUniquenessOfPost(String expectedText) {
        checkTextInElement(uniquePostText, expectedText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "'Delete Post button'");
        return new MyProfilePage(webDriver);
    }

    public PostPage checkIsRedirectedToPostPage() {
        Assert.assertTrue("URL does not contain /post/", webDriver.getCurrentUrl().contains("/post/"));
        return this;
    }
    public CreateNewPostPage clickOnEditButton() {
        clickOnElement(buttonEdit);
        return new CreateNewPostPage(webDriver);
    }
    public PostPage checkTitleIsPresent(String expectedTitle) {
        Assert.assertEquals("Title is not matched", expectedTitle, getTitleText());
        return this;
    }

    private String getTitleText() {
        return webDriver.findElement(By.xpath("//div[@class='post-title']")).getText();
    }
}
