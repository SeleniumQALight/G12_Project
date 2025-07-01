package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPostTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidData()
                .checkIsRedirectToHomePage()
                .createOnButtingCreateNewPost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoImputTitle("G12 - Krainyk Viktoriia post title")
                .enterTextIntoImputBody("Body of the post created by Krainyk Viktoriia")
                .clickOnUniquePostCheckbox("check") // or "checked" based on your test case)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkUniquenessOfPost()
                .checkTextInSuccessMessage("New post successfully created.")

        ;
    }
}
