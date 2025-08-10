package org.postsTest;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class ChangePostTest extends BaseTest {

    final String POST_TITLE = "TR005_G12 HW4_ Anj " + Utils_Custom.getDateAndTimeFormatted();
    final String UPDATED_TITLE = "HW4_UPDATED " + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void TC005_ChangePostTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectedToHomePage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonCreateNewPost()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Test for change new post")
                .clickOnButtonSaveNewPost()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnEditButton()
                .enterTextIntoInputTitle(UPDATED_TITLE)
                .clickOnButtonSaveUpdates()
                .checkTextInSuccessMessage("Post successfully updated.")
                .clickBackToPostPermalink()
                .checkTitleIsPresent(UPDATED_TITLE)
                .clickOnPostTitle(UPDATED_TITLE)
                .waitForPostTitleVisible(UPDATED_TITLE)
                .getheaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .checkPostWithTitleIsPresent(UPDATED_TITLE, 1);

    }
    @After
    public void cleanup() {
        logger.info("Post-condition: deleting posts if present");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeds()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .deletePostTillPresent(UPDATED_TITLE);
    }
}
