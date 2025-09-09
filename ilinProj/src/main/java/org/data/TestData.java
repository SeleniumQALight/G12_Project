package org.data;

import org.utils.ConfigProvider;

import java.util.Locale;

public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";
    public final static String VALID_NEW_LOGIN_UI = "new"+System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());

    public static final String VALID_LOGIN_API = "g12artemuser".toLowerCase();
    public static final String VALID_PASSWORD_API = "1234567890-=";

    public static final String VALID_LOGIN_API_BOOKS = "ArtemAqa";
    public static final String VALID_PASSWORD_API_BOOKS = "JafWqhEm@QtRW88";
}
