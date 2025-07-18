package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreateNewPostPage;
import org.pages.LoginPage;
import org.pages.MyProfilePage;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public void checkButtonSignOutVisible() {
//        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        checkIsElementDisplayed(buttonSignOut);
    }


    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public void checkButtonSignOutNotVisible() {
        checkIsElementNotDisplayed(buttonSignOut);
    }

    public LoginPage checkButtonCreatePostVisible() {
        checkIsElementDisplayed(buttonCreatePost);
        return new LoginPage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }
}


