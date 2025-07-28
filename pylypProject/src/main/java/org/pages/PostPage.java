package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage{
  
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectedToPostPage() {
        checkUrlWithPattern();
        //TODO check some elements on the page
        return this;
    }

    public PostPage checkIsSuccessMassageDisplayed() {
        checkIsElementDisplayed(successMessage);
        return this;
    }

    public PostPage checkTextIsSuccessMessage(String expectedMessageText) {
        checkTextInElement(successMessage, expectedMessageText);
        return this;
    }

    @FindBy(xpath = "//p[contains(text(),'Is this post unique?')]")
    private WebElement checkboxText;

    public PostPage checkCheckboxValueIs(String expectedValue) {
        String expectedText = "Is this post unique? : " + expectedValue;
        checkTextInElement(checkboxText, expectedText);
        return this;
      
    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "'Delete post button'");
        return new MyProfilePage(webDriver);
    }
}
