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
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        userRepository.findByEmail(signUpRequestDto.getEmail()).ifPresent(user -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with email =  " + signUpRequestDto.getEmail() + " already exist");
        });
        User user = modelMapper.map(signUpRequestDto, User.class);
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setActive(false);
      return   modelMapper.map(userRepository.save(user), SignUpResponseDto.class);

    }

    @Override
    public SignInResponseDto signIn(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "No such user with email = " + email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Wrong password or username");
        }

        user.setActive(true);
        userRepository.save(user);
        SignInResponseDto signInResponseDto = new SignInResponseDto();

        signInResponseDto.setToken(jwtTokenUtil.generateToken(user.getPassword()));
        signInResponseDto.setType(String.valueOf(user.getRoleType()));

        return signInResponseDto;
    }
}
