package org.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTests.LoginTestWithPageObject;
import org.pages.CreateNewPostPage;
import org.registrationTest.ValidationMessagesTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        ValidationMessagesTest.class
})
public class RegressionSuite {
}
