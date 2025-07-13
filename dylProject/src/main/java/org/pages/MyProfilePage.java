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


    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
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

    private List<WebElement> getListOfPostsWithTitle(String postTitle1, String postTitle2) {
        String xpath = String.format("//*[text()='%s' or text()='%s']", postTitle1, postTitle2);
                return webDriver.findElements(By.xpath(xpath));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedAmountOfPosts) {
        Assert.assertEquals(
                "Amount of posts with title " + postTitle + " is not equal to expected",
                expectedAmountOfPosts,
                getListOfPostsWithTitle(postTitle).size());
        logger.info("Post with title " + postTitle + " is present");
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getListOfPostsWithTitle(postTitle);
        final int MAX_POST_COUNT = 100; // postList.size()
        int counter = 0;
        while (!postsList.isEmpty() && counter < MAX_POST_COUNT) {
            clickOnElement(postsList.get(0),"Post with title '" + postTitle + "'");
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeletePresent();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getListOfPostsWithTitle(postTitle);
            counter++;
        }
        if (counter >= MAX_POST_COUNT) {
            logger.error("Number of posts with title " + postTitle + " is more than " + MAX_POST_COUNT);
        }
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle1, String postTitle2) {
        List<WebElement> postsList = getListOfPostsWithTitle(postTitle1,postTitle2);
        final int MAX_POST_COUNT = postsList.size();
        int counter = 0;
        while (!postsList.isEmpty() && counter < MAX_POST_COUNT) {
            WebElement post = postsList.get(0);
            String postTitle = post.getText();
            clickOnElement(post,"Post with title '" + postTitle + "'");
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeletePresent();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getListOfPostsWithTitle(postTitle1, postTitle2);
            counter++;
        }
        if (counter > MAX_POST_COUNT) {
            logger.error("Number of posts is more than " + MAX_POST_COUNT);
        }
        return this;
    }


    private MyProfilePage checkIsMessageSuccessDeletePresent() {
        checkIsElementDisplayed(successMessageDelete);
        return this;
    }

}
