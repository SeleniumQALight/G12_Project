package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends ParentPage{
    Logger logger = Logger.getLogger(getClass());

    private String postWithTitleLocator = "//*[text()='%s']";

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        // TODO: Implement URL check
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
}
