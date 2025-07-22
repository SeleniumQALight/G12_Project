package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class EditPostPage extends ParentPage{
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement buttonSaveUpdates;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "";
    }


    public EditPostPage clickOnButtonSaveUpdates(){
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

}
