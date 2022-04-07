package com.jaezi.web.config;


import com.alibaba.excel.EasyExcel;
import com.jaezi.synergia.model.Aggregat;
import com.jaezi.web.JaeziApplication;
//import com.lkx.util.ExcelUtil;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author warren
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/22 18:31
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JaeziApplication.class)
public class ExcelTest extends TestCase {

    @Test
    public void test() {
        System.out.println("123");
    }

    @Test
    public void ExcelTest() throws Exception {
        List<Aggregat> list = new ArrayList<>();

        Aggregat aggregat = new Aggregat();
        aggregat.setAmount("1");
        aggregat.setComponentNumber("1");
        aggregat.setMaterialDescription("测试");
        aggregat.setMaterialNumber("12");
        aggregat.setMaterialType(1);
        aggregat.setUnit("件");
        list.add(aggregat);
//        ExcelUtil.exportExcel("d:/1.xlsx",list,Aggregat.class);
    }


    @Test
    public void simpleWrite() {
        String filename = "d:/2.xlsx";
        // 2 调用easyexcel里面的方法实现写操作
        // write方法两个参数：第一个参数文件路径名称，第二个参数实体类class
        List<Aggregat> list = new ArrayList<>();
        Aggregat aggregat = new Aggregat();
        aggregat.setAmount("1");
        aggregat.setComponentNumber("1");
        aggregat.setMaterialDescription("测试");
        aggregat.setMaterialNumber("12");
        aggregat.setMaterialType(1);
        aggregat.setUnit("件");
        list.add(aggregat);
        EasyExcel.write(filename,Aggregat.class).sheet("学生列表").doWrite(list);
    }


}