package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage {


//    @FindBy(xpath = "//button[text()='Sign Out']")
//    private WebElement buttonSignOut;
//
//    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
//    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }



    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }


    public HomePage chekIsRedirectToHomePage() {
        //TODO check URL
        getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        return this;
    }


//    public void checkButtonSignOutVisible() {
//        checkIsElementDisplayed(buttonSignOut);
//    }
//
//    public CreateNewPostPage clickOnButtonCreatePost() {
//        clickOnElement(buttonCreatePost);
//        return new CreateNewPostPage(webDriver);
//    }
}
