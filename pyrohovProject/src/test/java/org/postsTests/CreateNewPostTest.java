package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    // GUID = 1a8f3e6e-2d3b-4e6f-9c3a-5b7e8d9f0a1b

    final String POST_TITLE = "G12 Pyrohov" +  Utils_Custom.getDateAndTimeFormatted();

    @Test

    public void createNewPost(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .chekIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post created by Valentyn")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccesMessage("New post successfully created.")

        ;

        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)

        ;
    }

}
