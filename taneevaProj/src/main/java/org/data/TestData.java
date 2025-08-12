package org.data;

import org.utils.ConfigProvider;

public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin", "qaauto");
//            ConfigProvider.configHiddenProperties.login());
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";
}


    public final static String INVALID_LOGIN_UI = "qaauto1";

}
