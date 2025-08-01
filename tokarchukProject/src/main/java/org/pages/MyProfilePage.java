package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

    private String postWithTitleLocator = "//*[text()='%s']";

    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successMessageDelete;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/profile/[a-zA-Z0-9]*";
    }

    public MyProfilePage checkIsRedirectedToMyProfilePage() {
        checkURLWithPattern();
        return this;
    }

    private List<WebElement> getListOfPostsWithTitle(String postTitle) {
        return webDriver.findElements(
                By.xpath(String.format(postWithTitleLocator, postTitle)));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedAmountOfPosts) {
        Assert.assertEquals(
                "Number of posts with title '" + postTitle + "'",
                expectedAmountOfPosts,
                getListOfPostsWithTitle(postTitle).size());
        logger.info("Posrt with title '" + postTitle + "' is present");
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postList = getListOfPostsWithTitle(postTitle);
        final int MAX_POST_COUNT = 100; //
        int counter = 0;
        while (!postList.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(postList.get(0),
                    "Post with title '" + postTitle + "'");
            new PostPage(webDriver)
                    .checkIsRedirectedToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectedToMyProfilePage()
                    .checkIsMessagesSuccessDeletePresent();
            logger.info("Post with title '" + postTitle + "' is deleted");
            postList = getListOfPostsWithTitle(postTitle);
            counter++; // counter = counter + 1;
        }
        if (counter >= MAX_POST_COUNT) {
            logger.error("Number of posts with title '" + postTitle + "' is more than " + MAX_POST_COUNT);
        }

        return this;
    }

    private MyProfilePage checkIsMessagesSuccessDeletePresent() {
        checkIsElementDisplayed(successMessageDelete);
        return this;
    }
}

