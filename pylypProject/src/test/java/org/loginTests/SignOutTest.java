package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {

    @Test
    public void userCanLogoutSuccessfully() {
        // 1. Логін
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred();

        // 2. Перевірки елементів у хедері після логіна
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().checkChatIconIsVisible();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().checkSearchIconIsVisible();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().isMyProfileIconVisible();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().isCreatePostButtonVisible();

        // 3. Logout
        pageProvider.getHomePage().clickOnButtonSignOut();

        // 4. Перевірки після логауту (LoginPage)
        pageProvider.getLoginPage()
                .checkIsRedirectedToLoginPage()
                .checkIsLoginInputVisible()
                .checkIsPasswordInputVisible()
                .checkIsSignInButtonVisible();
    }
}