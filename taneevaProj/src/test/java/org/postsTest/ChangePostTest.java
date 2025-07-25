package org.postsTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.Utils_Custom;

public class ChangePostTest extends BaseTest {

    final String POST_TITLE = "TR005_G12 HW4_ Anj" + Utils_Custom.getDateAndTimeFormatted();
    final String UPDATED_TITLE = "TR005_G12 HW4_ Anj_UPDATED" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void TC005_ChangePostTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFIllLoginFormWithValidCred()
                .checkIsRedirectedToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreateNewPost()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Test for change new post")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectedToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnEditButton()
                .enterTextIntoInputTitle(UPDATED_TITLE)
                .clickOnButtonSaveUpdates()
                .checkTextInSuccessMessage("Post successfully updated.")
                .checkTitleIsPresent(UPDATED_TITLE);

    }

}
