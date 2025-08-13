package org.postsTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.utils.ConfigProvider;
import org.utils.DB_Util_seleniumUsers;
import org.utils.ExcelSpreadsheetData;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import static org.utils.Utils_Custom.getDateAndTimeFormatted;

@RunWith(JUnitParamsRunner.class)
public class CreateNewPostWithDBAndExcelData extends BaseTest {
    private final String LOGIN = "newqaauto";
    private String password;
    DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
    private String postTitle;

    @After
    @Parameters(method = "parametersForTestCreateNewPost")
    public void deletePosts() throws SQLException, ClassNotFoundException {
        logger.info("Post condition - delete test");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(postTitle);
    }

    @Test
    @Parameters(method = "parametersForTestCreateNewPost")
    public void TC001_testCreateNewPostWithDBAndExcel(String title,
                                        String body,
                                        String accessGroup,
                                        String checkboxValue,
                                        String message,
                                        String isUnique) throws SQLException, ClassNotFoundException {
        password = dbUtilSeleniumUsers.getPassForLogin(LOGIN);
        postTitle = title;
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextInInputLogin(LOGIN)
                .enterTextInInputPassword(password)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonCreateNewPost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoImputTitle(title)
                .enterTextIntoImputBody(body)
                .selectTextInDropdownAccess(accessGroup)
                .clickOnUniquePostCheckbox(checkboxValue)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(message)
                .checkUniquenessOfPost(isUnique)
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsDisplayed(title, 1);
    }

    public Collection parametersForTestCreateNewPost() throws IOException {
        String pathToExcelFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("passToDataFile = " + pathToExcelFile);
        logger.info("sheetName = " + sheetName);
        logger.info("skipFirstRow = " + skipFirstRow);
        Collection<Object[]> parametersForTestCreateNewPost = new ExcelSpreadsheetData(new FileInputStream(pathToExcelFile), sheetName, skipFirstRow).getData();
        int testRound = 1;
        for (Object[] row : parametersForTestCreateNewPost) {
            row[0] = String.format((String) row[0], "G12_Krainyk_test_round_" + testRound, getDateAndTimeFormatted());
            row[1] = String.format((String) row[1], "created_by_Krainyk");
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
            testRound++;
        }
        return parametersForTestCreateNewPost;
    }
}