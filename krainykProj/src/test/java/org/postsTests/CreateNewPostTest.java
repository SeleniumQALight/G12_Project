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
    final String POST_UNIQUE = "check";

    @Test
    public void TC001_createNewPostTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidData()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreateNewPost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoImputTitle(POST_TITLE)
                .enterTextIntoImputBody(POST_BODY)
                .clickOnUniquePostCheckbox(POST_UNIQUE)
                .selectTextInDropdownAccess("Приватне повідомлення")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkUniquenessOfPost(POST_UNIQUE)
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
        logger.info("Post condition - delete test");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(POST_TITLE)
        ;
    }
}
