package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage {
    final String uniquePostTextTrueMessage = "Is this post unique? : yes";
    final String uniquePostTextFalseMessage = "Is this post unique? : yes";

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//p[contains(text(), 'Is this post unique?')]")
    private WebElement uniquePostText;

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

    public PostPage checkUniquenessOfPost() {
        checkTextInElement(uniquePostText, uniquePostTextTrueMessage);
        return this;
    }

    public PostPage checkNonUniquenessOfPost() {
        checkTextInElement(uniquePostText, uniquePostTextFalseMessage);
        return this;
    }
}
