package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreateNewPostPage;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreateNewPost;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
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

    public CreateNewPostPage createOnButtingCreateNewPost() {
        clickOnElement(buttonCreateNewPost);
        return new CreateNewPostPage(webDriver);
    }
}
