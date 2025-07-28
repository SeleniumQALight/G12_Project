package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.elements.HeaderForLoggedUserElement;

import java.time.Duration;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = "//a[@data-original-title='Edit']")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//a[contains(@class,'edit-post')]")
    private WebElement buttonEdit;

    @FindBy(xpath = "//p[contains(text(), 'Is this post unique?')]")
    private WebElement uniquePostText;

    @FindBy(xpath = "//a[contains(@class,'small') and contains(@class,'font-weight-bold') and contains(text(),'Back to post permalink')]")
    private WebElement backToPostPermalinkLink;

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
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        boolean urlContainsPost = wait.until(driver -> driver.getCurrentUrl().contains("/post/"));
        Assert.assertTrue("URL does not contain /post/", urlContainsPost);
        return this;
    }

    public CreateNewPostPage clickOnEditButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'toast-message') and contains(text(), 'Post successfully created.')]")));
        buttonEdit.click();
        return new CreateNewPostPage(webDriver);
    }
    public PostPage clickBackToPostPermalink() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(backToPostPermalinkLink));
        backToPostPermalinkLink.click();
        return this;
    }

    public PostPage checkTitleIsPresent(String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//div[contains(@class,'d-flex') and contains(@class,'justify-content-between')]//h2"),
                expectedTitle));
        Assert.assertEquals("Title is not matched", expectedTitle, getTitleText());
        return this;
    }

    private String getTitleText() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'d-flex') and contains(@class,'justify-content-between')]//h2")));
        return titleElement.getText();

    }
    public PostPage clickOnPostTitle(String postTitle) {
        By postTitleLocator = By.xpath("//a[contains(@class,'list-group-item') and .//strong[contains(text(), '" + postTitle + "')]]");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        WebElement postTitleElement = wait.until(ExpectedConditions.elementToBeClickable(postTitleLocator));
        postTitleElement.click();
        return this;
    }

    public PostPage waitForPostTitleVisible(String postTitle) {
        By postTitleLocator = By.xpath("//div[@class='post-title' and contains(text(), '" + postTitle + "')]");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(postTitleLocator));
        return this;
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
