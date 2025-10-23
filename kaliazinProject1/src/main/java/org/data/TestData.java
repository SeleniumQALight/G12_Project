package org.data;


import org.apache.poi.ss.formula.functions.Rate;

import java.util.HashMap;
import java.util.Map;

public class TestData {
    public final static String VALID_LOGIN_UI = "qaauto";
    public final static String VALID_PASSWORD_UI = "123456qwerty";

    public static final String VALID_LOGIN_API = "solido".toLowerCase();
    public static final String VALID_PASSWORD_API = "123456qwerty";

    public static final Map<String, Rate> apiRates = new HashMap<>();
    public static final Map<String, Rate> uiRates  = new HashMap<>();
}
