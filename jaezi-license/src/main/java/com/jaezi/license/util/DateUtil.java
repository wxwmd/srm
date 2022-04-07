package com.jaezi.license.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author warren
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 14:10
 * @description
 */
public class DateUtil {

    private final static String DATE_FORMAT = "yyyy-MM-dd";
    private final static String MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
    private final static String SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间
     *
     * @param
     * @return String
     * yyyy-MM-dd 时间
     * @author warren
     * @date 2021/7/26
     * @since 1.0
     */
    public static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.now().format(formatter);
    }

    /**
     * 获取当前时间
     *
     * @param
     * @return String
     * yyyy-MM-dd 时间
     * @author yzl
     * @date 2021/7/26
     * @since 1.0
     */
    public static String getCurrentDate(String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDate.now().format(formatter);
    }

    /**
     * 获取指定时间
     *
     * @param
     * @return String
     * yyyy-MM-dd 时间
     * @author yzl
     * @date 2021/7/26
     * @since 1.0
     */
    public static String getAssignDate(String dateFormat, int day) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDate.now().plusDays(day).format(formatter);
    }

    public static void main(String[] args) {
        System.out.println(getCurrentDate());
    }
}
