package suites;


import categories.SmokeTestsFilter;
import loginTest.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postsTest.CreateNewPostTest;
import registrationTest.ValidationMessagesTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestsFilter.class) // Assuming SmokeTestsFilter is a valid category
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        ValidationMessagesTest.class,
        CreateNewPostTest.class,
})
public class SmokeSuite {


}
