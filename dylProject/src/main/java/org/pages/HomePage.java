package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;


public class HomePage extends ParentPage {
//    Logger logger = Logger.getLogger(getClass());

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


    public HomePage checkIsRedirectToHomePage() {
// TODO check URL
        getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        return this;
    }


//    public void checkButtonSignOutVisible() {
////        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
//        checkIsElementDisplayed(buttonSignOut);
//    }
//
//
//    public CreateNewPostPage clickOnButtonCreatePost() {
//        clickOnElement(buttonCreatePost);
//        return new CreateNewPostPage(webDriver);
//    }
//
//    public void checkButtonSignOutNotVisible() {
//        checkIsElementNotDisplayed(buttonSignOut);
//    }
//
//    public LoginPage checkButtonCreatePostVisible() {
//        checkIsElementDisplayed(buttonCreatePost);
//        return new LoginPage(webDriver);
//    }


//    public boolean isButtonSignOutVisible() {
//        try {
//            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
//            logger.info("Element visible - " + state);
//            return state;
//        } catch (Exception e) {
//            logger.info("Element is not found");
//            return false;
//        }
//    }
}
