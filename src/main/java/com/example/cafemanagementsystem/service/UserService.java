package com.example.cafemanagementsystem.service;


import com.example.cafemanagementsystem.dto.request.SignUpRequestDto;
import com.example.cafemanagementsystem.dto.request.UserRequestDto;
import com.example.cafemanagementsystem.dto.responce.SignInResponseDto;
import com.example.cafemanagementsystem.dto.responce.SignUpResponseDto;

import javax.transaction.Transactional;

public interface UserService {



    @Transactional
    SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto);

    SignInResponseDto signIn(String username, String password);


}
