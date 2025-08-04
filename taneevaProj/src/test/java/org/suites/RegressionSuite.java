package org.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTests.LoginTestWithPageObject;
import org.pages.CreateNewPostPage;
import org.reggistrationTests.ValidationMessagesTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreateNewPostPage.class,
        ValidationMessagesTest.class
})
public class RegressionSuite {
}
