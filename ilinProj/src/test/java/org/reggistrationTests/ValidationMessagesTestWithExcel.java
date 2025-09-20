package org.reggistrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.utils.ConfigProvider;
import org.utils.ExcelSpreadsheetData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;


@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTestWithExcel extends BaseTest {
    Logger logger = Logger.getLogger(getClass());
    @Test
    @Parameters(method = "parametersForTestValidationMessages")
    public void TC03_testValidationMessages(String userName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorMessages(expectedMessages);

    }


    public Collection parametersForTestValidationMessages() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "registrationErrors";
        boolean skipFirstRow = false;
        logger.info("pathToDataFile = " + pathToDataFile);
        logger.info("sheetName = " + sheetName);
        logger.info("skipFirstRow = " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

//    public Object[][] parametersForTestValidationMessages(){
//        return new Object[][]{
//                {"tr", "tr1", "tr2", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
//                {"artem", "tr1", "tr2", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}
//
//        };
//    }

}
