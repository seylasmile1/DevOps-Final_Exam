package com.exam.idcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.exam")
public class IdcardApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdcardApplication.class, args);
    }
}