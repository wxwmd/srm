package com.jaezi.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    /**
     * 比较第一个日期是否大于第二个日期
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return true-大于;false-不大于
     */
    public static boolean localDateIsAfter(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isAfter(secondDate);
    }

    /**
     * 比较第一个日期是否大于第二个日期
     *
     * @param date string日期
     * @return LocalDate
     */
    public static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * yyyy-MM-dd格式时间转为毫秒值
     *
     * @param time yyyy-MM-dd
     * @return String 毫秒值
     * @author yx
     * @date 2021年8月31日14:56:04
     * @since 1.0
     */
    public static String timeTranslate(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(time);
        return String.valueOf(date.getTime());
    }

    public static void main(String[] args) {
        System.out.println(getCurrentDate());
    }
}
