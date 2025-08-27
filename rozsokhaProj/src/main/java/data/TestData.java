package data;

import utils.ConfigProvider;

public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin",
            ConfigProvider.configHiddenProperties.login());
//    public final static String VALID_LOGIN_UI = "qaauto";
    public final static String VALID_PASSWORD_UI = "123456qwerty";
    public final static String INVALID_LOGIN_UI = "qaauto11";
    public final static String INVALID_PASSWORD_UI = "1234qwerty";
}
