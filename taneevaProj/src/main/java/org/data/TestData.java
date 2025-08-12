package org.data;

import org.utils.ConfigProvider;

public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin", "qaauto");
    public final static String VALID_PASSWORD_UI = "123456qwerty";
    public final static String VALID_NEW_LOGIN_UI = "new"+System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());

}
