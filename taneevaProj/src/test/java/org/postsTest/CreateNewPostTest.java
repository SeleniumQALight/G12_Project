package org.postsTest;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.pages.elements.HeaderForLoggedUserElement;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    //GUID =

    final String POST_TITLE = "TR001_G12 Anj" + Utils_Custom.getDateAndTimeFormatted();


    @Test
    public void TR001_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFIllLoginFormWithValidCred()
                .checkIsRedirectedToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreateNewPost()
                .checkIsRedirectedToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post created by Anj")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .setUniquePostCheckbox("check")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkUniquenessOfPost("Is this post unique? : yes")
                .getheaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1);
    }

    private HeaderForLoggedUserElement getheaderForLoggedUserElement() {
        return null;
    }

    @After
    public void deletePost() {
        logger.info("Post condition - delete post");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeds()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .deletePostTillPresent(POST_TITLE)
        ;
    }

}
