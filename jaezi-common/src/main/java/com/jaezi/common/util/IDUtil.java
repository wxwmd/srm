package com.jaezi.common.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/4/14  19:27:01
 * @description
 */
public class IDUtil {

    /**
     * 自动生成时间戳id
     * @return
     */
    public static Integer getId(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        LocalDateTime dateTime = LocalDateTime.now();
        String date = dateTime.format(formatter);
        String s = date + Math.round((Math.random() + 1) * 1000);
        return Integer.parseInt(s);
    }
}
