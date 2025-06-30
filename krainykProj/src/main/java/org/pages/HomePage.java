package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreateNewPost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkButtonSignOutVisible() {
        checkIsElementDisplayed(buttonSignOut);
//        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    }

//    public boolean isButtonSignOutVisible() {
//        try {
//            boolean state = buttonSignOut.isDisplayed();
//            logger.info("Element visible: " + state);
//            return state;
//        } catch (Exception e) {
//            logger.error("Element is not found: " + e); // Log the exception message .getMessage()
//            return false;
//        }
//    }

    public HomePage checkIsRedirectToHomePage() {
        // TODO check URL
        checkButtonSignOutVisible();
        return this;
    }

    public CreateNewPostPage createOnButtingCreateNewPost() {
        clickOnElement(buttonCreateNewPost);
        return new CreateNewPostPage(webDriver);
    }
}
