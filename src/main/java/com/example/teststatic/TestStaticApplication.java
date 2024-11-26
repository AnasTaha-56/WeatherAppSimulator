package com.example.teststatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestStaticApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestStaticApplication.class, args);
        System.out.println("HIIII");
    }

}
