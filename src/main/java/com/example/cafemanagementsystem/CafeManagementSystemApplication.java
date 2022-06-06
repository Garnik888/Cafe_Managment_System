package com.example.cafemanagementsystem;

import com.example.cafemanagementsystem.domain.entity.User;
import com.example.cafemanagementsystem.domain.enums.UserRole;
import com.example.cafemanagementsystem.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication()

//exclude = { SecurityAutoConfiguration.class }
public class CafeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeManagementSystemApplication.class, args);
    }


}
