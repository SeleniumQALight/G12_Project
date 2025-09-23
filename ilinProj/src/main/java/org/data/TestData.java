package org.data;

import org.utils.ConfigProvider;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";
    public final static String VALID_NEW_LOGIN_UI = "new"+System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());

    public static final String VALID_LOGIN_API = "g12artemuser".toLowerCase();
    public static final String VALID_PASSWORD_API = "1234567890-=";

    public static final String VALID_LOGIN_API_BOOKS = "ArtemAqa";
    public static final String VALID_PASSWORD_API_BOOKS = "JafWqhEm@QtRW88";



    public static final Map<String, BigDecimal> apiRates = new HashMap<>();
    public static final Map<String, BigDecimal> uiRates  = new HashMap<>();

    public static String key(String ccy, String side) {
        return ccy.toUpperCase() + "_" + side.toLowerCase();
    }



    public final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    public final static String ERROR_EMAIL = "You must provide a valid email address.";
    public final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
}
