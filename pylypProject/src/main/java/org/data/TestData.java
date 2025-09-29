package org.data;

import org.utils.ConfigProvider;

public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin",
            ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";

    public static final String INVALID_LOGIN_UI = "wrongUser";
    public static final String INVALID_PASSWORD_UI = "wrongPass";
    public static final String VALID_LOGIN_API = "g12olesiauser".toLowerCase();
    public static final String VALID_PASSWORD_API = "123456qwerty";
    public static final String EXISTING_EMAIL_UI = "olesya.pilip@gmail.com";
    public static final String EXISTING_USERNAME_UI = "Olesia";
}
