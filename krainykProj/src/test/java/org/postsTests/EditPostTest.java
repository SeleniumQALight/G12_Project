package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.utils.Utils_Custom.getDateAndTimeFormatted;

public class EditPostTest extends BaseTest {
    final String POST_TITLE_FOR_EDIT = "TR005_G12 - Krainyk Viktoriia on " + getDateAndTimeFormatted();
    final String EDITED_POST_TITLE = "TR005_G12 - Title has beed edited by Krainyk Viktoriia at " + getDateAndTimeFormatted();

    @Before
    public void createTestPost() {
        logger.info("PRECONDITION: Create test post with unique title");
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidData()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreateNewPost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoImputTitle(POST_TITLE_FOR_EDIT)
                .enterTextIntoImputBody("Body of the post created by Krainyk Viktoriia")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .getHeaderForLoggedUserElement().clickOnButtonSignOut();
    }

    @Test
    public void TC005_editPostTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidData()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsDisplayed(POST_TITLE_FOR_EDIT, 1)
                .clickOnPostWithTitle(POST_TITLE_FOR_EDIT);
        pageProvider.getPostPage().checkIsRedirectToPostPage()
                .clickOnEditPostButton()
                .checkIsRedirectToEditPostPage()
                .editTextIntoInputTitleOnEditPage(EDITED_POST_TITLE)
                .clickOnSaveUpdatesButton()
                .checkIsSuccessPostEditedMessageDisplayed()
                .checkTextInSuccessPostEditedMessage("Post successfully updated.")
                .clickOnLinkBackToPostPermalink()
                .checkIsRedirectToPostPage()
                .checkTextInPostTitleOnPostPage(EDITED_POST_TITLE)
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsDisplayed(EDITED_POST_TITLE, 1);
    }

    @After
    public void deleteEditedOrNotEditedPost() {
        logger.info("POSTCONDITION: delete updated or not updated post");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(POST_TITLE_FOR_EDIT)
                .deletePostTillPresent(EDITED_POST_TITLE);
    }
}
