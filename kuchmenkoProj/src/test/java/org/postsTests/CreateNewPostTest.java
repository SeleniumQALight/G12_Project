package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {

    final String POST_TITLE = "TR001_G12 Kuchmenko" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void TR001_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreateNewPost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("G12 Kuchmenko Post Body")
                .clickOnUniquePostCheckbox("check")
                .selectTextInDropdownAccess("Приватне повідомлення") // Private post
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMassageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUnique()

        ;

        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .checkPostTitleIsDisplayed(POST_TITLE, 1)
        ;

    }

    @After
    public void deletePostAfterTest() {
        logger.info("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)
        ;
    }
}
