package org.postsTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.utils.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;


@RunWith(JUnitParamsRunner.class)

public class CreateNewPostTestWithExcelAndDB extends BaseTest {

    final String LOGIN_NEW = "newqaauto";
    private String pass_new;
    String currentTitle;

    DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();


    @Test

    @Parameters(method = "parametersForTestCreatePost")

    public void TR007_createNewPost(
            String title, String body, String accessGroup, String checkUnique,
            String message, String isUnique
    ) throws SQLException, ClassNotFoundException {
        currentTitle = title;
        pass_new = dbUtilSeleniumUsers.getPassForLogin(LOGIN_NEW);
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(LOGIN_NEW)
                .enterTextIntoPassword(pass_new)
                .clickOnButtonSignIn();

        pageProvider.getHomePage()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonCreateNewPost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(title)
                .enterTextIntoInputBody(body)
                .selectTextInDropdownAccess(accessGroup)
                .clickOnCheckboxUniguePost(checkUnique)
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(message)
                .checkIsPostUnique(isUnique)
        ;


        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(title, 1)

        ;

    }

    public Collection parametersForTestCreatePost() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcelData";
        logger.info("pathToDataFile = " + pathToDataFile);
        logger.info("sheetName " + sheetName);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName).getData();
    }

    @After
    public void deletePosts() {
        logger.info("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(currentTitle)

        ;


    }

}


