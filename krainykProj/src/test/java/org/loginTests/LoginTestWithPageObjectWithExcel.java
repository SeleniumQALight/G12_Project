package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.ConfigProvider;
import org.utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

public class LoginTestWithPageObjectWithExcel extends BaseTest {
    @Test
    public void TC002_validLoginTest() throws IOException {
        Map<String, String> dataForValidLogin =
               ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");

        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextInInputLogin(dataForValidLogin.get("login"))
                .enterTextInInputPassword(dataForValidLogin.get("pass"))
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonCreatePostVisible();

        pageProvider.getLoginPage().checkInputloginIsNotVisible()
                .checkInputPasswordIsNotVisible();
    }
}
