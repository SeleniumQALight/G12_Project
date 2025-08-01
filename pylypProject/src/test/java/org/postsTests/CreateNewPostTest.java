package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    // GUID

    final String POST_TITLE = "G12 Olesia Post Title" + Utils_Custom.getDateAndTimeFormatted();
    @Test
    public void TR001_createNewPost(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectedToHomePage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonCreatePost()
                .checkIsRedirectedToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("G12 Olesia Post Body")
                .setCheckboxState("check")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .clickOnSaveNewPostButton()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMassageDisplayed()
                .checkTextIsSuccessMessage("New post successfully created.")
                .checkCheckboxValueIs("yes")
        ;

        pageProvider.getPostsPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
        ;
    }

    @After
    public void deletePost() {
        logger.info("Post condition - delete");
pageProvider.getHomePage()
        .openHomePageAndLoginIfNeeded()
        .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
        .checkIsRedirectedToMyProfilePage()
        .deletePostsTillPresent(POST_TITLE)

;
    }
}
