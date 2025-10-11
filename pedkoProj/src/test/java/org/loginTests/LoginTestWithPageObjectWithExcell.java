package org.loginTests;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Test;
import org.utils.ConfigProvider;
import org.utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

public class LoginTestWithPageObjectWithExcell extends BaseTest {
    @Test
    public void validLogin() throws IOException {
        Map<String, String> dataForValidLogin =
        ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");

        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(dataForValidLogin.get("login"))
                .enterTextIntoPassword(dataForValidLogin.get("pass"))
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible();

    }

}
