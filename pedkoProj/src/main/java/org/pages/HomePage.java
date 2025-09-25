package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement createPostButton;

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement(){
        return new HeaderForLoggedUserElement(webDriver);
    }

    public HomePage  checkIsRedirectToHomePage() {
        // TODO check URL
        getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        return this;
    }

    public void checkButtonCreatePostVisible() {
        Assert.assertTrue("The Create Post button should be visible.", isElementDisplayed(createPostButton));
    }

}