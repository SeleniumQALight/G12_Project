package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
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
    @Test
    @Parameters(method = "parametersForTestValidationMessages")
    public void TC003_testValidationMessages(String userName,
                                             String email,
                                             String password,
                                             String expectedErrorMessages) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessages(expectedErrorMessages)
        ;
    }

    public Collection parametersForTestValidationMessages() throws IOException {
        String pathToExcelFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "registrationErrors";
        boolean skipFirstRow = false;
        logger.info("passToDataFile = " + pathToExcelFile);
        logger.info("sheetName = " + sheetName);
        logger.info("skipFirstRow = " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToExcelFile), sheetName, skipFirstRow).getData();
    }
}
