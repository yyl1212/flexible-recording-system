package com.wiw.pinyee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PinyeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PinyeeApplication.class, args);
    }
}
