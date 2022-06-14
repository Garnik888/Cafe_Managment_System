//package com.example.cafemanagementsystem;
//
//import com.example.cafemanagementsystem.domain.entity.User;
//import com.example.cafemanagementsystem.domain.enums.RoleType;
//import com.example.cafemanagementsystem.dto.request.SignUpRequestDto;
//import com.example.cafemanagementsystem.repository.UserRepository;
//import com.example.cafemanagementsystem.service.UserService;
//
//import org.h2.engine.Role;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.EmptyResultDataAccessException;
//
//
//import java.time.LocalTime;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class UserServiceTest {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private User user;
//
//
//    @BeforeEach
//    void beforeAll() {
//        user = new User();
//        user.setFirstName("poxos");
//        user.setLastName("poxos");
//        user.setUsername("poxoail");
//        user.setPassword("poxos");
//        user.setActive(true);
//        user.setRoleType(RoleType.WAITER);
//
//    }
//
//    @Test
//    void update() {
//     userRepository.save(user);
//
//        User byId = user.getUsername(user.getUsername());
//        assertEquals("dsf", byId.getPlane());
//    }
//
////    @Test
////    void save() {
////
////        userRepository.save(user);
////
////        Optional<User> byUsername = userRepository.findByUsername(user.getUsername());
////        assertTrue(byUsername.isPresent());
////        assertEquals("poxos", byUsername.get().getFirstName());
////        assertEquals("poxoail", byUsername.get().getUsername());
////    }
//
////    @Test
////    void deleteById() {
////        userService.save(user);
////        userService.deleteById(user.getId());
////        Optional<User> byId = userRepository.findById(user.getId());
////        assertFalse(byId.isPresent());
////    }
////
////    @Test
////    void deleteById_notFound() {
////        EmptyResultDataAccessException thrown = assertThrows(EmptyResultDataAccessException.class, () -> {
////            userService.deleteById(9999);
////        });
////        assertEquals("No class com.example.myitemsrest.entity.User entity with id 9999 exists!", thrown.getMessage());
////    }
//
//
//}
