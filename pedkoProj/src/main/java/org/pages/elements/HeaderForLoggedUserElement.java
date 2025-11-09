package org.pages.elements;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreateNewPostPage;
import org.pages.MyProfilePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class= 'btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//a[@class='text-white mr-2 header-search-icon']")
    private WebElement searchInput;

    @FindBy(xpath = "//span[@class='text-white mr-2 header-chat-icon']")
    private WebElement chatButton;

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement avatarIcon;

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public void checkButtonSignOutVisible() {
        checkIsElementDisplayed(buttonSignOut);
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public void checkButtonSignOutNotVisible() {
        Assert.assertFalse("The 'Sign Out' button should not be visible.", isElementDisplayed(buttonSignOut));
    }

    public HeaderForLoggedUserElement checkButtonCreatePostVisible() {
        Assert.assertTrue("The Create Post button should be visible.", isElementDisplayed(buttonCreatePost));
        return this;
    }

    public void clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        logger.info("Button Sign Out was clicked");
    }

    public HeaderForLoggedUserElement checkHeaderElementsVisible() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(searchInput));

        Assert.assertTrue("Search input should be visible", isElementDisplayed(searchInput));
        Assert.assertTrue("Chat button should be visible", isElementDisplayed(chatButton));
        Assert.assertTrue("Avatar should be visible", isElementDisplayed(buttonMyProfile));
        Assert.assertTrue("Create Post button should be visible", isElementDisplayed(buttonCreatePost));
        Assert.assertTrue("Sign Out button should be visible", isElementDisplayed(buttonSignOut));
        logger.info("All header elements are visible after login.");
        return this;
    }

    public HeaderForLoggedUserElement checkHeaderElementsNotVisible() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.invisibilityOf(searchInput));
        wait.until(ExpectedConditions.invisibilityOf(chatButton));
        wait.until(ExpectedConditions.invisibilityOf(buttonMyProfile));
        wait.until(ExpectedConditions.invisibilityOf(buttonCreatePost));
        wait.until(ExpectedConditions.invisibilityOf(buttonSignOut));

        Assert.assertFalse("Search input should not be visible", isElementDisplayed(searchInput));
        Assert.assertFalse("Chat button should not be visible", isElementDisplayed(chatButton));
        Assert.assertFalse("Avatar should not be visible", isElementDisplayed(buttonMyProfile));
        Assert.assertFalse("Create Post button should not be visible", isElementDisplayed(buttonCreatePost));
        Assert.assertFalse("Sign Out button should not be visible", isElementDisplayed(buttonSignOut));

        logger.info("All header elements are hidden after logout.");
        return this;
    }

    private boolean isElementDisplayedSafe(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}