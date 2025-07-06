package org.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsCustom {
    private UtilsCustom() {
    }

    public static String getDateAndTimeFormatted() {
        return getDateAndTime("yyyy-MM-dd_HH-mm-ss");
    }

    public static String getDateAndTime(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }
}
