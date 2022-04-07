package com.jaezi.license;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class LicenseFrame extends JFrame {

    public LicenseFrame() {
        initFrame();
    }

    public void initFrame() {
        // 确保一个漂亮的外观风格
        this.setDefaultLookAndFeelDecorated(true);
        this.setTitle("License生成器");
        //是否允许拖动窗口改变大小
        this.setResizable(false);
        //设置关闭窗体时，关闭JVM
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗体大小
        this.setSize(350, 290);
        //获取屏幕宽度,设置居中
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int) (toolkit.getScreenSize().getWidth() - this.getWidth()) / 2;
        int y = (int) (toolkit.getScreenSize().getHeight() - this.getHeight()) / 2;
        //设置窗体显示位置
        this.setLocation(x, y);
        //使用表格布局
        this.setLayout(new GridLayout(3, 1));
        try {
            InputStream inputStream = LicenseFrame.class.getClassLoader().getResourceAsStream("qy.png");
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);

            //设置图标
            this.setIconImage(new ImageIcon(bytes).getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //设置背景色
//        this.setBackground(Color.GRAY);
    }
}
