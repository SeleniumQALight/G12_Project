package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.utils.Utils_Custom;

public class EditPostTest extends BaseTest {

    final String POST_TITLE_EDITED ="TR004_G12 Ador EDITED" + Utils_Custom.getDateAndTimeFormatted() ;
    final String POST_TITLE = "TR004_G12 Ador" + Utils_Custom.getDateAndTimeFormatted();


    @Before

    public void loginAndCreatePost() {
        logger.info("Pre-condition - log in and create a post");

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreateNewPost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("'Post Body created by Ador'")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .clickOnCheckboxUniguePost()
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkUniquenessOfPost()
        ;
        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
        ;

    }

    @Test
    public void TR004_editPost() {
        logger.info("Test TR004 'Edit Post' started");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .clickOnPostTitle(POST_TITLE)
                .clickOnEditButton()
                .checkIsRedirectToEditPostPage()
                .enterTextIntoInputTitle(POST_TITLE_EDITED)
                .clickOnButtonSaveUpdates()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE_EDITED, 1)
        ;

    }

    @After
    public void deletePosts() {
        logger.info("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE_EDITED)
                .deletePostsTillPresent(POST_TITLE)

        ;

    }


}
