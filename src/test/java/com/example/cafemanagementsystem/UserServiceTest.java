//package com.example.cafemanagementsystem;
//
//import com.example.cafemanagementsystem.domain.entity.User;
//import com.example.cafemanagementsystem.domain.enums.RoleType;
//import com.example.cafemanagementsystem.dto.request.SignUpRequestDto;
//import com.example.cafemanagementsystem.repository.UserRepository;
//import com.example.cafemanagementsystem.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.EmptyResultDataAccessException;
//
//import java.nio.file.attribute.UserPrincipalNotFoundException;
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
//    private SignUpRequestDto signUpRequestDto;
//
//    private User user;
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @BeforeEach
//    void beforeAll() {
//        signUpRequestDto = new SignUpRequestDto();
//        signUpRequestDto.setFirstName("firstName");
//        signUpRequestDto.setLastName("lastName");
//        signUpRequestDto.setUsername("username");
//        signUpRequestDto.setPassword("password");
//        signUpRequestDto.setUserRoleType(RoleType.ADMIN);
//
//    }
//
//    @Test
//    void save() throws UserPrincipalNotFoundException {
//        user = modelMapper.map(signUpRequestDto, User.class);
//        user.setId(15L);
//        user.setActive(true);
//        userRepository.save(user);
//       //assertEquals(1, userRepository.count());
//        Optional<User> byId = userRepository.findById(user.getId());
//        assertTrue(byId.isPresent());
//        assertEquals("firstName", byId.get().getFirstName());
//        assertEquals("username", byId.get().getUsername());
//    }
//
//    @Test
//    void deleteById() throws UserPrincipalNotFoundException {
//        userRepository.delete(user);
//        userService.deleteUser(user.getUsername());
//        Optional<User> byId = userRepository.findById(user.getId());
//        assertFalse(byId.isPresent());
//    }
//
//    @Test
//    void getById_notFound() {
//        EmptyResultDataAccessException thrown = assertThrows(EmptyResultDataAccessException.class, () -> {
//            userService.findById(99L);
//        });
//        assertEquals("No class com.example.myitemsrest.entity.User entity with id 9999 exists!", thrown.getMessage());
//    }
//
//
//}