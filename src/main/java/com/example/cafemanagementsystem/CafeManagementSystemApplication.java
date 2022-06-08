package com.example.cafemanagementsystem;

import com.example.cafemanagementsystem.domain.entity.User;
import com.example.cafemanagementsystem.domain.enums.UserRole;
import com.example.cafemanagementsystem.repository.UserRepo;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication()
@OpenAPIDefinition(
        info = @Info(
                title = "Cafe Manager",
                version = "v1",
                description = "API for Cafe",
                contact = @Contact(name = "bootcamp", email = "aca.bootcamp@gmail.com")))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer")

public class CafeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeManagementSystemApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner runner(UserRepository userRepository, BCryptPasswordEncoder encoder) {
//        return args -> {
//            User manager = new User();
//            manager.setFirstName("Vardan");
//            manager.setLastName("Vardanyan");
//            manager.setUsername("manager");
//            manager.setPassword(encoder.encode("password"));
//            manager.setManager(true);
//
//            userRepository.save(manager);
//        };
//    }

}
