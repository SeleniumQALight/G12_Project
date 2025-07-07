package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

import static org.utils.Utils_Custom.getDateAndTimeFormatted;

public class CreateNewPostTest extends BaseTest {
    // GUID for unique identification of the test
    // randomly generated class, but it is not garanteed to be unique
    // date and time of creation of the test
    final String POST_TITLE = "TR001_G12 - Krainyk Viktoriia on " + getDateAndTimeFormatted();
    final String POST_BODY = "Body of the post created by Krainyk Viktoriia";

    @Test
    public void TR001_createNewPostTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidData()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().createOnButtingCreateNewPost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoImputTitle(POST_TITLE)
                .enterTextIntoImputBody(POST_BODY)
                .clickOnUniquePostCheckbox("check")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkUniquenessOfPost()
                .checkTextInSuccessMessage("New post successfully created.")
        ;

        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsDisplayed(POST_TITLE, 1)
        ;
    }

    @After
    public void deletePosts() {
        // TODO: Implement deletion of posts created by the test
    }
}
