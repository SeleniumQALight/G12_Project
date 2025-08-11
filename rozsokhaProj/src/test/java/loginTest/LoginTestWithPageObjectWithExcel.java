package loginTest;

import baseTest.BaseTest;
import org.junit.Test;
import utils.ConfigProvider;
import utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObjectWithExcel extends BaseTest {
    @Test
    public void validLogin() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");


//        pageProvider.getLoginPage().openLoginPage();
//        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
//        pageProvider.getLoginPage().enterTextIntoPassword(VALID_PASSWORD_UI);
//        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(dataForValidLogin.get("login"))
                .enterTextIntoPassword(dataForValidLogin.get("pass"))
                .clickOnButtonSignIn(); // Chaining method calls for better readability

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible();


    }
}
