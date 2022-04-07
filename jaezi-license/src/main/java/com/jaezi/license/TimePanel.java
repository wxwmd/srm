package com.jaezi.license;

import com.eltima.components.ui.DatePicker;

import javax.swing.*;
import java.awt.*;

public class TimePanel extends JPanel {

    public TimePanel(DatePicker startDatePicker, DatePicker stopDatePicker) {
        initTimePanel(startDatePicker, stopDatePicker);
    }

    public void initTimePanel(DatePicker startDatePicker, DatePicker stopDatePicker) {
        //使用流式布局
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel startTimeLabel = new JLabel("开始时间：");
        this.add(startTimeLabel);
        this.add(startDatePicker);

        JLabel stopTimeLabel = new JLabel("到期时间：");
        this.add(stopTimeLabel);
        this.add(stopDatePicker);
    }

}
