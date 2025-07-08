package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    //    GUID for the post: 1f8b0c2e-3d4e-11ec-8d3d-0242ac130003
    final String POST_TITLE = "TR001_G12 Andrii " + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void TR001_createNewPost() {
        pageProvider.getLoginPage().
                openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().checkButtonCreatePostVisible()
                .checkInputUserNameAndPasswordNotVisible();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("This is a body of the post created by G12 Andrii on 26.06.2025")
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
                .checkPostWithTitleIsPresent(POST_TITLE, 1);

    }

    @After
    public void deletePosts() {
        logger.info("Postcondition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)
        ;
    }

}