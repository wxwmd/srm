package com.jaezi.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/5/19 18:09
 * @description
 */
@SpringBootApplication(scanBasePackages = "com.jaezi")
public class JaeziApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaeziApplication.class, args);
    }

}
