package com.example.cafemanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication()
//exclude = { SecurityAutoConfiguration.class }
public class CafeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeManagementSystemApplication.class, args);
    }

}
