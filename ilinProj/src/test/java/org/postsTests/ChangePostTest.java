package org.postsTests;

import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.utils.Utils_Custom;

public class ChangePostTest extends BaseTest {

    Logger logger = Logger.getLogger(getClass());

    final String POST_TITLE_FOR_CHANGE = "Homework Post Artem " + Utils_Custom.getDateAndTimeFormatted();
    @Before
    public void createPost() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred()
                    .checkIsRedirectToHomePage()
                    .getHeaderForLoggedUserElement().clickOnButtonCreatePost()
                    .checkIsRedirectToCreateNewPostPage()
                    .enterTextIntoInputTitle(POST_TITLE_FOR_CHANGE)
                    .enterTextIntoInputBody("G12 Artem Post Body")
                    .selectTextInDropdownAccess("Приватне повідомлення")
                    .clickOnButtonSaveNewPost()
                    .checkIsRedirectToPostPage()
                    .checkIsSuccessMessageDisplayed()
                    .checkTextInSuccessMessage("New post successfully created.");
    }
    @Test
    public void changePost() {
        pageProvider.getPostPage()
                    .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                    .checkIsRedirectToMyProfilePage()
                    .checkNewPostTitleIsPresent(POST_TITLE_FOR_CHANGE, 1)
                    .clickOnPostTitle(POST_TITLE_FOR_CHANGE);
        pageProvider.getPostPage().checkIsRedirectToPostPage()
                    .clickOnEditPostButton()
                    .checkIsRedirectToEditPostPage()
                    .enterEditedTextIntoInputTitle(POST_TITLE_FOR_CHANGE + " - Edited")
                    .clickOnButtonSaveEditPost()
                    .checkTextInSuccessEditMessage("Post successfully updated.")
                    .clickOnMyProfileButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkNewPostTitleIsPresent(POST_TITLE_FOR_CHANGE + " - Edited", 1)
                    ;
    }


    @After
    public void deletePost() {
        logger.info("Post condition - delete posts");
        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE_FOR_CHANGE + " - Edited");

    }
}
