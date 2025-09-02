package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

    private String postWithTitleLocator = "//*[text()='%s']";

    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successMessageDelete;

    @FindBy(xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/profile/[a-zA-Z0-9]*";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {

        checkUrlWithPattern();
        return this;
    }

    private List<WebElement> getListOfPostsWithTitle(String postTitle) {
        return webDriver.findElements(
                By.xpath(String.format(postWithTitleLocator, postTitle)));

    }

    public MyProfilePage checkNewPostTitleIsPresent(String postTitle, int expectedAmount) {
        Assert.assertEquals(
                "Amount of posts with title '" + postTitle + "'",
                expectedAmount,
                getListOfPostsWithTitle(postTitle).size());
        logger.info("Post with title '" + postTitle + "' is present");
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getListOfPostsWithTitle(postTitle);
        final int MAX_POSTS_COUNT = 100; // to avoid infinite loop
        int counter = 0;
        while (!postsList.isEmpty() && counter < MAX_POSTS_COUNT) {
            clickOnElement(postsList.get(0), "Post with title '" + postTitle + "'");
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeletePresent();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getListOfPostsWithTitle(postTitle);
            counter++;
        }
        if (counter >= MAX_POSTS_COUNT) {
            logger.error("Number of posts with title  " + postTitle + "' is more than " + MAX_POSTS_COUNT);

        }
        return this;


    }

    private MyProfilePage checkIsMessageSuccessDeletePresent() {
        checkIsElementDisplayed(successMessageDelete);
        return this;

    }

    public void clickOnPostTitle(String postTitleChange) {
        List<WebElement> postsList = getListOfPostsWithTitle(postTitleChange);
        if (postsList.isEmpty()) {
            logger.error("Post with title '" + postTitleChange + "' is not present");
            Assert.fail("Post with title '" + postTitleChange + "' is not present");
        } else {
            clickOnElement(postsList.get(0), "Post with title '" + postTitleChange + "'");
            new PostPage(webDriver).checkIsRedirectToPostPage();
        }
    }

    public MyProfilePage checkNumberOfPostsInPosts(String numberOfPosts) {
        Assert.assertEquals("Number of posts", numberOfPosts, postsList.size());
        return this;
    }
}