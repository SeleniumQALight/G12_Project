package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
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

    public HomePage checkButtonSignOutVisible() {
        //  Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        checkIsElementDisplayed(buttonSignOut);
        return this;
    }

    public void checkButtonSignOutNotVisible(){
       checkIsElementNotDisplayed(buttonSignOut);
       // Assert.assertFalse("Sign Out button should not be visible",isElementDisplayed(buttonSignOut));
    }

    public void checkButtonCreatePostVisible() {
        checkIsElementDisplayed(buttonCreateNewPost);
    }

    public HomePage checkIsRedirectToHomePage() {
        //TODO check URL
        checkButtonSignOutVisible();
        return this;
    }

    public CreateNewPostPage clickOnButtonCreateNewPost() {
        clickOnElement(buttonCreateNewPost);
        return new CreateNewPostPage(webDriver);
    }




//    private boolean isButtonSignOutVisible() {
//        try {
//            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
//            logger.info(" element visible - " + state);
//            return state;
//        } catch (Exception e) {
//            logger.info("Element is not found");
//            return false;
//        }
//    }
}

