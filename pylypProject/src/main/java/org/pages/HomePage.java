package org.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;

import javax.net.ssl.HostnameVerifier;

public class HomePage extends ParentPage{
//    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkButtonSignOutVisible() {
        checkIsElementDisplayed(buttonSignOut);
    }

    public void checkIsButtonCreatePostVisible() {
        checkIsElementDisplayed(buttonCreatePost);
    }

    public HomePage checkIsRedirectedToHomePage() {
        //TODO check URL
        checkButtonSignOutVisible();
        return this;
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    // перевірка, що інпут логіна зник (не існує в DOM)
    public HomePage checkIsLoginInputNotVisible() {
        Assert.assertFalse("Login input should not be visible on HomePage",
                isElementDisplayed("//input[@placeholder='Username']"));
        return this;
    }

    // перевірка, що інпут пароля зник (не існує в DOM)
    public HomePage checkIsPasswordInputNotVisible() {
        Assert.assertFalse("Password input should not be visible on HomePage",
                isElementDisplayed("//input[@placeholder='Password']"));
        return this;
    }
}
