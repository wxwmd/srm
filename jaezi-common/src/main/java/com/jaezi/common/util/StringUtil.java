package com.jaezi.common.util;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/1/15 14:37
 * @description
 */
public final class StringUtil {

    private StringUtil(){}

    public static boolean isEmpty(String str){
        return null == str || str.length() == 0;
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    public static boolean isBlank(String str){
        return null == str || str.length() == 0 || str.trim().length() == 0;
    }

    public static boolean isNotBlank(String str){
        return !isBlank(str);
    }

    public static boolean isNull(String str){
        return null == str || str.length() == 0 || str.trim().length() == 0 || "null".equalsIgnoreCase(str.trim());
    }

    public static boolean isNotNull(String str){
        return !isNull(str);
    }
}
