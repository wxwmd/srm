package com.jaezi.license;

import com.eltima.components.ui.DatePicker;
import com.jaezi.license.util.DatePickerUtil;

import javax.swing.*;

public class LicenseServerStart {

    private static void createLicenseGUI() {
        JTextField companyTextField = new JTextField(23);
        JTextField principalTextField = new JTextField(23);
        JTextField phoneTextField = new JTextField(23);
        DatePicker startDatePicker = DatePickerUtil.getDatePicker();
        DatePicker stopDatePicker = DatePickerUtil.getDatePicker();

        JFrame frame = new LicenseFrame();

        JPanel infoPanel = new InfoPanel(companyTextField, principalTextField, phoneTextField);
        frame.getContentPane().add(infoPanel);

        JPanel timePanel = new TimePanel(startDatePicker, stopDatePicker);
        frame.getContentPane().add(timePanel);

        JPanel createPanel = new CreatePanel(companyTextField, principalTextField, phoneTextField, startDatePicker, stopDatePicker);
        frame.getContentPane().add(createPanel);

        //显示窗口
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        // 显示应用 GUI
        javax.swing.SwingUtilities.invokeLater(() -> {
            createLicenseGUI();
        });
    }

}
