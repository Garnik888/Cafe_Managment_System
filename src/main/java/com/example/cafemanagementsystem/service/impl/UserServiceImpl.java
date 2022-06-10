package com.example.cafemanagementsystem.service.impl;

import com.example.cafemanagementsystem.domain.entity.User;
import com.example.cafemanagementsystem.dto.request.SignUpRequestDto;
import com.example.cafemanagementsystem.dto.responce.SignInResponseDto;
import com.example.cafemanagementsystem.dto.responce.SignUpResponseDto;
import com.example.cafemanagementsystem.repository.UserRepo;
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

@Service
public class UserServiceImpl implements UserService {


    private final UserRepo userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepository, JwtTokenUtil jwtTokenUtil, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) throws UserPrincipalNotFoundException {
        userRepository.findByUsername(signUpRequestDto.getUsername()).ifPresent(user -> {
            try {
                throw new UserPrincipalNotFoundException( "User with username = " + signUpRequestDto.getFirstName() + "already exist");
            } catch (UserPrincipalNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        User user = modelMapper.map(signUpRequestDto, User.class);
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setActive(false);
      return   modelMapper.map(userRepository.save(user), SignUpResponseDto.class);

    }

    @Override
    public SignInResponseDto signIn(String username, String password) throws UserPrincipalNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->new UserPrincipalNotFoundException(
                        "No such user with username = " + username));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Wrong password or username");
        }
        user.setActive(true);
        userRepository.save(user);
        SignInResponseDto signInResponseDto = new SignInResponseDto();

        signInResponseDto.setToken(jwtTokenUtil.generateToken(user));
        signInResponseDto.setType(String.valueOf(user.getRoleType()));

        return signInResponseDto;
    }
}
