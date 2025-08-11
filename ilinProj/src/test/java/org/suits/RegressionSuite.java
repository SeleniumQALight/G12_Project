package org.suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTests.LoginTestWithPageObject;
import org.postsTests.CreateNewPostTest;
import org.reggistrationTests.ValidationMessagesTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreateNewPostTest.class,
        ValidationMessagesTest.class
})
public class RegressionSuite {

}
