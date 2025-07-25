package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObjectWithExcel extends BaseTest {
    @Test
    public void validLogin() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");

        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(dataForValidLogin.get("login"))
                .enterTextIntoPassword(dataForValidLogin.get("pass"))
                .clickOnButtonSignIn();
        pageProvider.getLoginPage().checkInputUserNameAndPasswordNotVisible();

        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkButtonCreatePostVisible();
    }

}