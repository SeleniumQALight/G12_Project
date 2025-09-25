package org.data;

import org.utils.ConfigProvider;

import java.util.HashMap;
import java.util.Map;

public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";

    public static final String VALID_LOGIN_API = "g12tarasuser".toLowerCase();
    public static final String VALID_PASSWORD_API = "123456qwerty";

    public static Map<String, Double> ratesForCurrencyApi = new HashMap<>();
    public static Map<String, Double> ratesForCurrencyUi = new HashMap<>();
}
