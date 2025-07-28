package org.postsTests;

import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    // GUID = 1b1c0b1-2f3d-4e5f-6a7b-8c9d0e1f2g3h
    Logger logger = Logger.getLogger(getClass());

    final String POST_TITLE = "TR001_G12 Artem " + Utils_Custom.getDateAndTimeFormatted();
    @Test
    public void TR001_createPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("G12 Artem Post Body")
                .clickOnCheckboxForUniquePost("check")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUnique()
        ;

        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkNewPostTitleIsPresent(POST_TITLE, 1)
        ;

    }

    @After
    public void deletePost() {
        logger.info("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE);

    }

}
