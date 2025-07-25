package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pages.CreateNewPostPage;
import org.pages.PostPage;

public class EditPostTest extends BaseTest {

    private String originalTitle = "AutoTitle_" + System.currentTimeMillis();
    private String updatedTitle = originalTitle + "_edited";

    @Before
    public void createPost() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred();

        pageProvider.getHomePage().clickOnCreatePostButton();
        pageProvider.getCreateNewPostPage().enterTextIntoInputTitle(originalTitle)
                .enterTextIntoInputBody("This is the body of the post")
                .selectTextInDropdownAccess("Загальнодоступне")
                .clickOnSaveNewPostButton()
                .checkIsSuccessMessageDisplayed();
    }

    @Test
    public void userCanEditPostTitle() {
        // 1. Перейти в My Profile
        pageProvider.getHomePage().getHeaderForLoggedUserElement().clickOnButtonMyProfile();

        // 2. Натиснути на пост із originalTitle
        pageProvider.getMyProfilePage().clickOnPostWithTitle(originalTitle);

        // 3. Натиснути кнопку "Edit"
        pageProvider.getPostsPage().clickOnEditButton();

        // 4–5. Очистити поле title і ввести updatedTitle, натиснути Save Updates
        CreateNewPostPage createNewPostPage = pageProvider.getCreateNewPostPage();
        createNewPostPage.enterTextIntoInputTitle(updatedTitle);
        PostPage postPage = createNewPostPage.clickOnSaveUpdatesButton();

        // перевірка повідомлення про успішне оновлення
        postPage.checkTextIsSuccessMessage("Post successfully updated.");

       // 6. Перевірити, що оновлений тайтл є у списку постів
        pageProvider.getHomePage().getHeaderForLoggedUserElement().clickOnButtonMyProfile();
        pageProvider.getMyProfilePage().checkPostWithTitleIsPresent(updatedTitle, 1);
    }

    @After
    public void deletePost() {
        pageProvider.getHomePage().getHeaderForLoggedUserElement().clickOnButtonMyProfile();

        pageProvider.getMyProfilePage().deletePostsTillPresent(updatedTitle);
        new PostPage(pageProvider.getWebDriver()).checkTextIsSuccessMessage("Post successfully deleted.");

        pageProvider.getMyProfilePage().deletePostsTillPresent(originalTitle);
        new PostPage(pageProvider.getWebDriver()).checkTextIsSuccessMessage("Post successfully deleted.");
    }
}