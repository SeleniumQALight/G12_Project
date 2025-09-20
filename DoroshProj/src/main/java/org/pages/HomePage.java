package org.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import data.TestData;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

//    @FindBy(xpath = "//button[text()='Sign Out']")
//    private WebElement buttonSignOut;
//    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
//    private WebElement buttonCreateNewPost;


    public HomePage(WebDriver webDriver) {
        super(webDriver);

    }

    @Override
    protected String getRelativeURL() {
        return "/";
    }
    @Step

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }
    @Step

    public HomePage checkIsRedirectToHomePage() {
        checkURL();
        getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        return this;
    }
    @Step

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (getHeaderForLoggedUserElement().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }

//    public void checkButtonSignOutVisible() {
//        //  Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
//        checkIsElementDisplayed(buttonSignOut);
//    }
//
//
//
//    public CreateNewPostPage clickOnButtonCreateNewPost() {
//        clickOnElement(buttonCreateNewPost);
//        return new CreateNewPostPage(webDriver);
//    }


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

