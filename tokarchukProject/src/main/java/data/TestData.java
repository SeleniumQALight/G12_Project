package data;

import org.utils.ConfigProvider;

public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin",
   ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI  = "123456qwerty";

    public static final String INVALID_LOGIN_UI = "failedLogin";
    public static final String INVALID_PASSWORD_UI = "failedPassword";
    public static final String INVALID_LOGIN_ERROR_MESSAGE = "Invalid username/password.";


}
