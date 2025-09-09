package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{
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

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedAmountOfPosts) {
        Assert.assertEquals(
                "Number of posts with title '" + postTitle + "'",
                expectedAmountOfPosts,
                getListOfPostsWithTitle(postTitle).size());
        logger.info("Post with title '" + postTitle + "' is present");
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getListOfPostsWithTitle(postTitle);
        final int MAX_POST_COUNT = 100; // postsList.size()
        int counter = 0;
        while (!postsList.isEmpty() && (counter < MAX_POST_COUNT)){
            clickOnElement(postsList.get(0),
                    "Post with title '" + postTitle + "'");
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeletePresent();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getListOfPostsWithTitle(postTitle);
            counter++; // counter = counter + 1;
        }
        if (counter >= MAX_POST_COUNT){
            logger.error("Number of posts with title " + postTitle + " is more than " + MAX_POST_COUNT);
        }
        return this;
    }

    private MyProfilePage checkIsMessageSuccessDeletePresent() {
        checkIsElementDisplayed(successMessageDelete);
        return this;
    }

    public MyProfilePage checkNumberOfPosts(int numberOfPosts) {
        Assert.assertEquals("Number of posts ", numberOfPosts, postsList.size());

        return this;
    }
}
