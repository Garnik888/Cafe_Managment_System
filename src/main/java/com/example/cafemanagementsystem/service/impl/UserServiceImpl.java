package com.example.cafemanagementsystem.service.impl;

import com.example.cafemanagementsystem.domain.entity.User;
import com.example.cafemanagementsystem.domain.enums.RoleType;
import com.example.cafemanagementsystem.dto.request.SignUpRequestDto;
import com.example.cafemanagementsystem.dto.responce.SignInResponseDto;
import com.example.cafemanagementsystem.dto.responce.SignUpResponseDto;
import com.example.cafemanagementsystem.dto.responce.UserResponseDto;
import com.example.cafemanagementsystem.repository.UserRepository;
import com.example.cafemanagementsystem.service.UserService;
import com.example.cafemanagementsystem.util.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtTokenUtil jwtTokenUtil, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) throws UserPrincipalNotFoundException {
        userRepository.findByUsername(signUpRequestDto.getUsername()).ifPresent(user -> {
            try {
                throw new UserPrincipalNotFoundException("User with username = " + signUpRequestDto.getFirstName() + "already exist");
            } catch (UserPrincipalNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        User user = modelMapper.map(signUpRequestDto, User.class);
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setActive(false);
        return modelMapper.map(userRepository.save(user), SignUpResponseDto.class);

    }

    @Override
    public SignInResponseDto signIn(String username, String password) throws UserPrincipalNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserPrincipalNotFoundException(
                        "No such user with username = " + username));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Wrong password or username");
        }
        user.setActive(true);
        userRepository.save(user);
        SignInResponseDto signInResponseDto = new SignInResponseDto();

        signInResponseDto.setToken(jwtTokenUtil.generateToken(user.getUsername()));
        signInResponseDto.setType(String.valueOf(user.getRoleType()));

        return signInResponseDto;
    }

    @Override
    public UserResponseDto deleteUser(String username) throws UserPrincipalNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UserPrincipalNotFoundException(String.format("Order with username %s is not found", username)));
        userRepository.delete(user);

        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto findById(Long userId) throws UserPrincipalNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserPrincipalNotFoundException(String.format("User with id %s is not found", userId)));
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> findByRoleTypeWaiter() throws UserPrincipalNotFoundException {
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        List<User> users = userRepository.findAllByRoleType(RoleType.WAITER);

        if(users.isEmpty()) {
            throw new UserPrincipalNotFoundException("Not found waiter's role type users ");
        }

        for (User user : users) {

            userResponseDtoList.add(modelMapper.map(user, UserResponseDto.class));

        }
        return userResponseDtoList;
    }

    @Override
    public List<UserResponseDto> findAll() throws UserPrincipalNotFoundException {

        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        List<User> users = userRepository.findAll();

        if(users.isEmpty()) {
            throw new UserPrincipalNotFoundException("Not found users");
        }

        for (User user : users) {

            userResponseDtoList.add(modelMapper.map(user, UserResponseDto.class));

        }
        return userResponseDtoList;
    }

    @Override
    public UserResponseDto updatePassword(String username, String newPassword) throws UserPrincipalNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UserPrincipalNotFoundException(String.format("User with username %s is not found", username)));
        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
        return modelMapper.map(user,UserResponseDto.class);
    }

}
