package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.enums.CheckboxState;

public class CreateNewPostTest extends BaseTest {

    @Test
    public void createNewPost() {
        final CheckboxState UNIQUE_STATE = CheckboxState.CHECK;

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectedToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(" G12 Nataliia Post Title")
                .enterTextIntoInputBody("G12 Nataliia Post Body")
                .setUniquePostCheckboxState(UNIQUE_STATE)
                .clickOnSaveNewPost()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMessageDisplayed()
                .verifyUniquePostState(UNIQUE_STATE)
                .checkTextInSuccessMessage("New post successfully created.")
        ;
    }
}
