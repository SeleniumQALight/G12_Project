package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.utils.Utils_Custom;

public class EditPostTest extends BaseTest {
    final String ORIGINAL_POST_TITLE = "TR002_G12 Nataliia Post Original "
            + Utils_Custom.getDateAndTimeFormatted();
    final String EDITED_POST_TITLE = "TR002_G12 Nataliia Post Edited "
            + Utils_Custom.getDateAndTimeFormatted();

    @Before
    public void createPostBeforeTest() {
        logger.info("Precondition - create a new post");
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectedToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(ORIGINAL_POST_TITLE)
                .enterTextIntoInputBody("G12 Nataliia Post Body Before Edit")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .clickOnSaveNewPost()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.");
    }

    @Test
    public void editPostTitle() {
        logger.info("Test - edit post title");

        // редагування поста
        pageProvider.getPostPage()
                .clickOnEditPostButton()
                .checkIsRedirectToEditPostPage()
                .enterTextIntoInputTitle(EDITED_POST_TITLE)
                .clickOnSaveUpdatedPost()
                .checkIsRedirectedToPostPage()
                .checkTextInSuccessMessage("Post successfully updated.");

        // перевірка у MyProfile
        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .checkPostWithTitleIsPresent(EDITED_POST_TITLE, 1);
    }

    @After
    public void deletePostsAfterTest() {
        logger.info("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .deletePostsTillPresent(EDITED_POST_TITLE)
                .deletePostsTillPresent(ORIGINAL_POST_TITLE);
    }
}
