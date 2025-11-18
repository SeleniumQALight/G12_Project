package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;
import org.pages.PostPage;

public class EditPostTest extends BaseTest {
    final String POST_TITLE = "TR004_G12 " + Utils_Custom.getDateAndTimeFormatted();
    final String NEW_TITLE = "TR004_G12_EDITED " + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void editPostTitleTest() {
        // precondition: login
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage();

        // create post
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonCreatePost();

        pageProvider.getCreateNewPostPage()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("body")
                .selectUniquePostCheckbox(true)
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed();

        // go to My Profile and edit post
        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage();

        pageProvider.getMyProfilePage()
                .clickOnPostWithTitle(POST_TITLE)
                .checkIsRedirectToPostPage()
                .clickOnButtonEdit()
                .checkIsRedirectToEditPostPage()
                .enterTextIntoInputTitle(NEW_TITLE)
                .clickOnButtonSaveUpdatedPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed();

        // verify updated title present in MyProfile
        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(NEW_TITLE, 1);
    }

    @After
    public void cleanup() {
        // delete posts with both titles
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .delitePostsTillPresent(NEW_TITLE)
                .delitePostsTillPresent(POST_TITLE);
    }
}