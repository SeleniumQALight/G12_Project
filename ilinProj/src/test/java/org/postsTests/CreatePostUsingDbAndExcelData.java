package org.postsTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.utils.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

import static org.data.TestData.VALID_NEW_LOGIN_UI;

import java.sql.SQLException;


@RunWith(JUnitParamsRunner.class)
public class CreatePostUsingDbAndExcelData extends BaseTest {
    private Database mySQLDataBase;
    private Logger logger = Logger.getLogger(getClass());
    private String postTitle;




    @Test
    @Parameters(method = "parametersForTestCreatePostUsingDbAndExcelData")
    public void TC02_testCreatePostUsingDbAndExcelData(String title, String postBody,String dropdownValue, String checkboxState, String successMessage, String isUnique) throws SQLException, ClassNotFoundException {
        DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
        String password = dbUtilSeleniumUsers.getPassForLogin(VALID_NEW_LOGIN_UI);
        postTitle = title;
        pageProvider.getLoginPage()
                    .openLoginPage()
                    .enterTextIntoInputLogin(VALID_NEW_LOGIN_UI)
                    .enterTextIntoPassword(password)
                    .clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsRedirectToHomePage()
                    .getHeaderForLoggedUserElement().clickOnButtonCreatePost()
                    .checkIsRedirectToCreateNewPostPage()
                    .checkIsRedirectToCreateNewPostPage()
                    .enterTextIntoInputTitle(title)
                    .enterTextIntoInputBody(postBody)
                    .clickOnCheckboxForUniquePost(checkboxState)
                    .selectTextInDropdownAccess(dropdownValue)
                    .clickOnButtonSaveNewPost()
                    .checkIsRedirectToPostPage()
                    .checkIsSuccessMessageDisplayed()
                    .checkTextInSuccessMessage(successMessage)
                    .checkIsPostUnique(isUnique);
        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkNewPostTitleIsPresent(title, 1);


    }

    public Collection<Object[]> parametersForTestCreatePostUsingDbAndExcelData() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcel";
        logger.info("pathToDataFile = " + pathToDataFile);
        logger.info("sheetName = " + sheetName);
        Collection<Object[]> parametersForTestCreateNewPost = new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName).getData();
        int count = 1;
        for (Object[] column : parametersForTestCreateNewPost) {
            column[0] = String.format((String) column[0], "G12_Artem_Test" + count, "_" + Utils_Custom.getDateAndTimeFormatted());
            column[1] = String.format((String) column[1], "G12_Artem_Test" + count);
            try {
                if ("All Users".equals(column[2])) {
                    column[2] = "Загальнодоступне";
                } else if ("One Person".equals(column[2])) {
                    column[2] = "Приватне повідомлення";
                } else if ("Group Message".equals(column[2])) {
                    column[2] = "Групове повідомлення";
                }
            } catch (Exception e) {
                logger.error("Unknown value in table" + column[2], e);
            }
            count ++;
        }
        return parametersForTestCreateNewPost;

    }

    @After
    @Parameters(method = "parametersForTestCreatePostUsingDbAndExcelData")
    public void deletePost() {
        logger.info("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(postTitle);

    }
}
