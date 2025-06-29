package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost() {
        pageProvider.getLoginPage().
                openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .checkButtonCreatePostVisible()
                .checkInputUserNameAndPasswordNotVisible();
        pageProvider.getHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextInInputTitle("G12 Andrii Post-26.06.2025")
                .enterTextInInputBody("This is a body of the post created by G12 Andrii on 26.06.2025")
                .selectUniquePostCheckbox("check")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUnique()
        ;
    }
}
