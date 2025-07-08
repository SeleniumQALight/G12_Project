package org.pages;

import org.enums.CheckboxState;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//p[contains(text(), 'Is this post unique?')]")
    private WebElement uniquePostInfo;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectedToPostPage() {
        //TODO check URL
        //TODO check some unique element on the page
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

    public PostPage verifyUniquePostState(CheckboxState expectedState) {
        String expectedText = (expectedState == CheckboxState.CHECK)
                ? "Is this post unique? : yes"
                : "Is this post unique? : no";
        checkTextInElement(uniquePostInfo, expectedText);
        return this;
    }
}
