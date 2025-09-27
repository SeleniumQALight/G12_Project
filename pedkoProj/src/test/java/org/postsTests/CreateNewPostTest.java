package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    //GUID = 3f4e8f3e-2d3a-4b6e-8f3e-2d3a4b6e8f3e
    final String POST_TITLE = "TR001_G12 Dariia Pedko Title 4" + Utils_Custom.getDateAndTimeFormatted();
    @Test
    public void TR001_createNewPost(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("G12 Dariia Pedko post body")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.");

        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
        ;
    }

    @After
    public void deletePost(){
        logger.info("Post condition - delete posts ");
        pageProvider.getHomePage()
         .openHomePageAndLoginIfNeeded()
         .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
         .checkIsRedirectToMyProfilePage()
         .delitePostsTillPresent(POST_TITLE);
    }
}
