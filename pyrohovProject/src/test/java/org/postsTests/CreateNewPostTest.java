package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {

    @Test

    public void createNewPost(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .chekIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle("G12 Pyrohov Post Title")
                .enterTextIntoInputBody("G12 Pyrohov Post Body")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccesMessage("New post successfully created.")

        ;

        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()

        ;
    }

}
