package org.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;

public class HomePage extends ParentPage {
//    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkButtonSignOutVisible() {
        checkIsElementDisplayed(buttonSignOut);
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

    // перевірка, що кнопка Sign Out не показується
    public HomePage checkIsButtonSignOutNotVisible() {
        Assert.assertFalse("Sign Out button should not be visible", isElementDisplayed(buttonSignOut));
        return this;
    }

    // перевірка, що інпут логіна зник (не існує в DOM)
    public HomePage checkIsLoginInputNotVisible() {
        Assert.assertFalse("Login input should not be visible on HomePage",
                isElementDisplayed(inputLogin));
        return this;
    }

    // перевірка, що інпут пароля зник (не існує в DOM)
    public HomePage checkIsPasswordInputNotVisible() {
        Assert.assertFalse("Password input should not be visible on HomePage",
                isElementDisplayed(inputPassword));
        return this;
    }

    // перевірка, що після логіну є кнопка Create Post
    public HomePage checkIsButtonCreatePostVisible() {
        checkIsElementDisplayed(buttonCreatePost);
        return this;
    }
}