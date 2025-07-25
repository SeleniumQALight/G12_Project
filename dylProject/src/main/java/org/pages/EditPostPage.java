package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class EditPostPage extends ParentPage{
    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    public EditPostPage clickOnSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

}
