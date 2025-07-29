package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.enums.CheckboxState;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    //GUID =
    final String POST_TITLE = "TR001_G12 Nataliia Post Title" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void createNewPost() {
        final CheckboxState UNIQUE_STATE = CheckboxState.CHECK;

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectedToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(" G12 Nataliia Post Title")
                .enterTextIntoInputBody("G12 Nataliia Post Body")
                .clickOnSaveNewPost()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
        ;

        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
        ;
    }
    @After
    public void deletePosts() {
        logger.info("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE);
    }
}
