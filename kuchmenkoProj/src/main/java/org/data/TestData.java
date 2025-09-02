package org.data;

import org.utils.ConfigProvider;

public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin",
            ConfigProvider.configHiddenProperties.login());

    public final static String VALID_PASSWORD_UI = "123456qwerty";

    public static final String VALID_LOGIN_API = "kkuchmenko".toLowerCase();

    public static final String VALID_PASSWORD_API = "1234567qwert";
}
