package com.jaezi.license.util;

import com.eltima.components.ui.DatePicker;

import java.awt.*;
import java.util.Date;
import java.util.Locale;

public class DatePickerUtil {

    public static DatePicker getDatePicker() {
        final DatePicker datePicker;
        // 格式
        String DefaultFormat = "yyyy-MM-dd";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.BOLD, 14);

        Dimension dimension = new Dimension(200, 24);

        datePicker = new DatePicker(date, DefaultFormat, font, dimension);
        // 设置国家
        datePicker.setLocale(Locale.CHINA);
        // 设置时钟面板可见
        datePicker.setTimePanleVisible(false);
        return datePicker;
    }

}
