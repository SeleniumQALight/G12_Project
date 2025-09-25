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

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }
    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO: implement check URL
        return this;
    }


    private List<WebElement> getListOfPostsWithTitle(String postTitle) {
        return webDriver.findElements(
                By.xpath(String.format(postWithTitleLocator, postTitle))
        );
    }


    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedAmountOfPosts) {
        Assert.assertEquals(
                "Number of posts with title " + postTitle + "'",
                expectedAmountOfPosts,
                getListOfPostsWithTitle(postTitle).size());
        logger.info("Post with title '" + postTitle + "' is present");
        return this;
    }

    public MyProfilePage deletePostTillPresent(String postTitle) {
        List<WebElement> postList = getListOfPostsWithTitle(postTitle);
        final int MAX_POST_COUNT = 100; // postList.size();
        int counter = 0;
        while (!postList.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(postList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMassageSuccessDeletePresent();
            logger.info("Post with title '" + postTitle + "' was deleted");
            postList = getListOfPostsWithTitle(postTitle);
            counter++;
        }
        if (counter >= MAX_POST_COUNT) {
            logger.error("Number of posts wih title " + postTitle + " is more than " + MAX_POST_COUNT);

        }

        return this;
    }

    private MyProfilePage checkIsMassageSuccessDeletePresent() {
        checkIsElementDisplayed(successMessageDelete);

        return this;
    }
}
