package org.databaseTests;


import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utils.DB_Util_seleniumTable;
import org.utils.Database;
import org.utils.MySQL_Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DatabaseTest {
    private Database mySQLDatabase;
    private final Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUpDatabase() throws SQLException, ClassNotFoundException {
        mySQLDatabase = MySQL_Database.getDataBase();
        logger.info("MySQL database was setup");
    }

    @After
    public void tearDown() throws SQLException {
        mySQLDatabase.quit();
        logger.info("MySQL database connection was closed");
    }

    @Test
    public void testDataFromDatabase() throws SQLException, ClassNotFoundException {
        ArrayList<Map<String, String>> dataFromSeleniumTable = mySQLDatabase.selectTableAsMap("SELECT * FROM seleniumTable");
        logger.info(dataFromSeleniumTable);
        logger.info("Size = " + dataFromSeleniumTable.size());

        final String LOGIN = "G12_nedbailo";

        dataFromSeleniumTable = mySQLDatabase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");

        Assert.assertEquals(0, dataFromSeleniumTable.size());

        int numberOfCreatedRows = mySQLDatabase.changeTable("INSERT INTO seleniumTable VALUES('7893459', '%s', '%s')", LOGIN, "12345");

        Assert.assertEquals(1, numberOfCreatedRows);

        dataFromSeleniumTable = mySQLDatabase.selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");

        Assert.assertEquals(1, dataFromSeleniumTable.size());

        int numberOfDeletedRows = mySQLDatabase.changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);

        Assert.assertEquals("Number of deleted rows ", 1, numberOfDeletedRows);

        logger.info("------------------------");

        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();

        dbUtilSeleniumTable.getPassForLogin("G10_masha_dd");
    }
}
