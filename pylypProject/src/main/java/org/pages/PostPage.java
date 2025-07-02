package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage{
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectedToPostPage() {
        //TODO check URL
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
}
