package com.jaezi.license;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    public InfoPanel(JTextField companyTextField, JTextField principalTextField, JTextField phoneTextField) {
        initInfoPanel(companyTextField, principalTextField, phoneTextField);
    }

    public void initInfoPanel(JTextField companyTextField, JTextField principalTextField, JTextField phoneTextField) {
        //设置背景色
//        this.setBackground(Color.GRAY);
        //设置大小
        this.setSize(350, 80);
        //使用流式布局
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel companyLabel = new JLabel("公司：");
        this.add(companyLabel);
        this.add(companyTextField);

        JLabel principalLabel = new JLabel("负责人：");
        this.add(principalLabel);
        this.add(principalTextField);

        JLabel phoneLabel = new JLabel("电话：");
        this.add(phoneLabel);
        this.add(phoneTextField);
    }

}
