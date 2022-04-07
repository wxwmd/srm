package com.jaezi.license;

import com.alibaba.fastjson.JSON;
import com.eltima.components.ui.DatePicker;
import com.jaezi.license.util.LicenseCodec;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class CreatePanel extends JPanel {

    public CreatePanel(JTextField companyTextField, JTextField principalTextField, JTextField phoneTextField,
                       DatePicker startDatePicker, DatePicker stopDatePicker) {
        initCreatePanel(companyTextField, principalTextField, phoneTextField, startDatePicker, stopDatePicker);
    }

    public void initCreatePanel(JTextField companyTextField, JTextField principalTextField, JTextField phoneTextField,
                                DatePicker startDatePicker, DatePicker stopDatePicker) {
//        this.setBackground(Color.red);
        // 生成按钮
        JButton createButton = new JButton("生成");
        createButton.setBounds(10, 10, 80, 25);
        createButton.addActionListener(e -> {
            //点击事件
            String company = companyTextField.getText();
            String principal = principalTextField.getText();
            String phone = phoneTextField.getText();
            String startDate = startDatePicker.getText();
            String stopDate = stopDatePicker.getText();
            String msg = "";
            if (StringUtils.isEmpty(company)) {
                msg = "公司未填写";
            } else if (StringUtils.isEmpty(principal)) {
                msg = "负责未填写！";
            } else if (StringUtils.isEmpty(phone)) {
                msg = "电话未填写！";
            } else if (StringUtils.isEmpty(startDate)) {
                msg = "开始日期未填写！";
            } else if (StringUtils.isEmpty(stopDate)) {
                msg = "结束日期未填写！";
            }
            if (StringUtils.isEmpty(msg)) {
                Map<String, String> map = new HashMap<>();
//                map.put("company", company);
//                map.put("principal", principal);
//                map.put("phone", phone);
//                map.put("startDate", startDate);
                map.put("stopDate", stopDate);
                //填写完成后生成lic
                try {
                    LicenseCodec.encrypt(JSON.toJSONString(map));
                    JOptionPane.showMessageDialog(this, "License生成成功", "提示", JOptionPane.WARNING_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "License生成失败", "提示", JOptionPane.WARNING_MESSAGE);
                } finally {
                    companyTextField.setText("");
                    principalTextField.setText("");
                    phoneTextField.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(this, msg, "提示", JOptionPane.WARNING_MESSAGE);
            }
        });
        this.add(createButton);
    }

    public void cleanInfo() {

    }
}
