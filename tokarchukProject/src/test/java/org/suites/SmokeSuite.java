package org.suites;

import org.categories.SmokeTestsFillter;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTests.LoginTestWithPageObject;
import org.pages.CreateNewPostPage;
import org.registrationTest.ValidationMessagesTest;

    @RunWith(Categories.class)
    @Categories.IncludeCategory(SmokeTestsFillter.class)
    @Suite.SuiteClasses({
            LoginTestWithPageObject.class,
            ValidationMessagesTest.class,
            CreateNewPostPage.class
            })
    public class SmokeSuite {
    }
