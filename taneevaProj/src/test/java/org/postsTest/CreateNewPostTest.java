package org.postsTest;

import org.baseTest.BaseTest;
import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    //GUID =

    final String POST_TITLE = "TR001_G12 Anj" + Utils_Custom.getDateAndTimeFormatted();
    @Test
    public void TR001_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFIllLoginFormWithValidCred()
                .checkIsRedirectedToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreatePost()
                .checkIsRedirectedToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body of the post created by Anj")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")

;
        pageProvider.getPostPage()
                .getheaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1);
    }

    @After
    public void deletePost() {
//        pageProvider.getMyProfilePage()
//                .getListOfPostsWithTitle(POST_TITLE)
//                .forEach(post -> post.findElement(By.xpath(".//button[text()='Delete']")).click());
//        Utils_Custom.waitABit(2);
}
