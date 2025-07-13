package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement sucessMessage;

    @FindBy(xpath = "//p[text() = 'Is this post unique? : yes']")
    private WebElement checkboxState;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForLoggedUserElement
    getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        // TODO check URL
        // TODO check some unique element on the page

        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementDisplayed(sucessMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        checkTextInElement(sucessMessage, expectedMessageText);
        return this;
    }

    public PostPage checkCheckboxSetState() {
        checkIsElementDisplayed(checkboxState);
        return this;
    }
}
