package com.lti.utilities;

public class StringUtility {

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.trim().length() == 0;
    }


}
