package org.suites;

import org.categories.SmokeTestsFilter;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTests.LoginTestWithPageObject;
import org.postsTest.CreateNewPostTest;
import org.reggistrationTests.ValidationMessagesTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestsFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        ValidationMessagesTest.class,
        CreateNewPostTest.class
})
public class SmokeSuite {
}
