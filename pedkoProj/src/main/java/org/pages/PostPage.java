package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;
import org.pages.EditPostPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PostPage extends ParentPage {

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//p[text() = 'Is this post unique? : yes']")
    private WebElement uniquePostMessage;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = "//a[@data-original-title='Edit']")
    private WebElement buttonEdit;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/post/[a-zA-Z0-9]*";
    }

    public HeaderForLoggedUserElement
    getHeaderForLoggedUserElement(){
        return new HeaderForLoggedUserElement(webDriver); }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
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

    public void checkIsPostUnique() {
        checkIsElementDisplayed(uniquePostMessage);
    }

    public MyProfilePage clickOnDeliteButton() {
        clickOnElement(buttonDeletePost, "Delete Post button");
        return new MyProfilePage(webDriver);
    }

    public EditPostPage clickOnButtonEdit() {
        // явное ожидание, пока элемент будет кликабельным
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(buttonEdit));

        clickOnElement(buttonEdit, "Edit button");
        return new EditPostPage(webDriver);
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        return new EditPostPage(webDriver);
    }
}