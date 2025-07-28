package org.postTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.utils.ConfigProvider;
import org.utils.DB_Util_seleniumUsers;
import org.utils.ExcelSpreadsheetData;
import org.utils.Utils_Custom;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class CreateNewPostTestWithExelAndDataBase extends BaseTest {
    private final String LOGIN = "newqaauto";
    private String password;
    private DB_Util_seleniumUsers dbUtilSeleniumUsers;
    private String lastPostTitle;

    @Before
    public void setUpDB() {
        dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
    }

    @Test
    @Parameters(method = "parametersForTestCreateNewPost")
    public void createNewPostWithExelAndDataBase(
            String title, String body, String accessGroup, String checkUnique,
            String message, String isUnique) throws SQLException, ClassNotFoundException {
        lastPostTitle = title;
        password = dbUtilSeleniumUsers.getPassForLogin(LOGIN);
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(LOGIN)
                .enterTextIntoPassword(password)
                .clickOnButtonSignIn();
        pageProvider.getHomePage()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextInInputTitle(title)
                .enterTextInInputBody(body)
                .selectTextInDropdownAccess(accessGroup)
                .selectUniquePostCheckbox(checkUnique)
                .clickOnSaveNewPostButton()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(message)
                .checkIsPostUnique(isUnique);

        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(title, 1);
    }

    @After
    @Parameters(method = "parametersForTestCreateNewPost")
    public void deletePosts() {
        logger.info("Postcondition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(lastPostTitle)
        ;
    }
    public Collection<Object[]> parametersForTestCreateNewPost() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() +
                "testDataSuit.xls";
        String sheetName = "createPostWithExcel";
        logger.info("path to file with data = " + pathToDataFile);
        logger.info("sheet name = " + sheetName);
        Collection<Object[]> parametersForTestCreateNewPost =
                new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName).getData();
        int count = 1;
        for (Object[] row : parametersForTestCreateNewPost) {
            row[0] = String.format((String) row[0], "G12 Andrii_" + count, "_" + Utils_Custom.getDateAndTimeFormatted());
            row[1] = String.format((String) row[1], "G12 Andrii_" + count);
            try {
            if ("All Users".equals(row[2])) {
                row[2] = "Загальнодоступне";
            } else if ("One Person".equals(row[2])) {
                row[2] = "Приватне повідомлення";
            } else if ("Group Message".equals(row[2])) {
                row[2] = "Групове повідомлення";
            }
            } catch (Exception e) {
                logger.error("Unknown value " + row[2], e);
            }
            count ++;
        }
        return parametersForTestCreateNewPost;
}

}
