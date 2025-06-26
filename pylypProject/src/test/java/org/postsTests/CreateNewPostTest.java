package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectedToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectedToCreateNewPostPage()
                .enterTextIntoInputTitle("G12 Olesia Post Title")
                .enterTextIntoInputBody("G12 Olesia Post Body")
                .clickOnSaveNewPostButton()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMassageDisplayed()
                .checkTextIsSuccessMessage("New post successfully created.")
        ;
    }
}
