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

    private final String POST_TITLE_LOCATOR_STRING = "//div[@class='post-title']";
    private final By postTitleLocator = By.xpath(POST_TITLE_LOCATOR_STRING);

    private final String CLICKABLE_POST_TITLE_LOCATOR_TEMPLATE =
            "//a[contains(@class,'list-group-item') and .//strong[contains(text(), '%s')]]";

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = "//a[@data-original-title='Edit']")
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

    public CreateNewPostPage clickOnEditButton() {
        clickOnElement(buttonEdit);
        return new CreateNewPostPage(webDriver);
    }

    public PostPage clickBackToPostPermalink() {
        clickOnElement(backToPostPermalinkLink);
        return this;
    }
    protected void waitUntilTextToBePresentInElement(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public PostPage checkTitleIsPresent(String expectedTitle) {
        waitUntilTextToBePresentInElement(webDriver.findElement(postTitleLocator), expectedTitle);
        Assert.assertEquals("Title is not matched", expectedTitle, getTitleText());
        return this;
    }

    private String getTitleText() {
        return webDriver.findElement(postTitleLocator).getText();
    }

    public PostPage clickOnPostTitle(String postTitle) {
        By clickableTitleLocator = By.xpath(String.format(CLICKABLE_POST_TITLE_LOCATOR_TEMPLATE, postTitle));
        clickOnElement(webDriver.findElement(clickableTitleLocator));
        return this;
    }
    protected void waitUntilElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public PostPage waitForPostTitleVisible(String postTitle) {
        By locator = By.xpath("//div[@class='post-title' and contains(text(), '" + postTitle + "')]");
        waitUntilElementVisible(locator);
        return this;
    }
}
