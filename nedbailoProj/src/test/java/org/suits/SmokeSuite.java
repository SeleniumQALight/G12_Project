package org.suits;

import org.categories.SmokeTestFilters;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTests.LoginTestWithPageObject;
import org.postsTest.CreateNewPostTest;
import org.registrationTests.ValidationMessagesTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilters.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreateNewPostTest.class,
        ValidationMessagesTest.class
})
public class SmokeSuite {

}
