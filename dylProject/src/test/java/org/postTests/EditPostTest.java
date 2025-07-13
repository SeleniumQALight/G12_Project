package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.utils.Utils_Custom;

public class EditPostTest extends BaseTest {
    final String POST_TITLE_FOR_EDIT = "TR002_G12 Andrii " + Utils_Custom.getDateAndTimeFormatted();
    final String POST_TITLE_AFTER_EDIT = "TR002_G12 Andrii EDIT " + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void editPostTest() {
        logger.info("START TEST");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement()
                .clickOnButtonSearch()
                .enterTextIntoInputSearch(POST_TITLE_FOR_EDIT)
                .clickOnPostTitle(POST_TITLE_FOR_EDIT)
                .checkIsRedirectToPostPage()
                .clickOnEditButton()
                .enterTextInInputTitle(POST_TITLE_AFTER_EDIT);
        pageProvider.getPostPage()
                .clickOnSaveUpdatesButton()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("Post successfully updated.")
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE_AFTER_EDIT, 1);
    }

    @Before
    public void LoginAndCreatePost() {
        logger.info("PRECONDITION: LogIn and create post with unique title");
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().checkButtonCreatePostVisible();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextInInputTitle(POST_TITLE_FOR_EDIT)
                .enterTextInInputBody("This is a body of the post created by G12 Andrii on 13.07.2025")
                .selectUniquePostCheckbox("check")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUnique()
        ;
        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE_FOR_EDIT, 1);

    }

    @After
    public void deleteChangedOrNotChangedPost() {
        logger.info("POSTCONDITION: delete changed or not changed post");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE_FOR_EDIT,POST_TITLE_AFTER_EDIT);
    }
}



