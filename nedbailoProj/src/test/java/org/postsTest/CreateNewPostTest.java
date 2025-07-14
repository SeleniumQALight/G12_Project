package org.postsTest;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.UtilsCustom;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TR001_G12 nedbailo" + UtilsCustom.getDateAndTimeFormatted();
    @Test
    public void TR001_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFIllLoginFormWithValidCred()
                .checkIsRedirectedToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreatePost()
                .checkIsRedirectedToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post created by Nedbailo")
                .selectTextInDropDownAccess("Приватне повідомлення")
                .setUniquePostCheckbox("check")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")

        ;
        pageProvider.getPostPage()
                .getheaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1);
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
