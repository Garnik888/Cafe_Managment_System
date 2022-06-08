package com.example.cafemanagementsystem.service;


import com.example.cafemanagementsystem.dto.request.SignUpRequestDto;
import com.example.cafemanagementsystem.dto.request.UserRequestDto;
import com.example.cafemanagementsystem.dto.responce.SignInResponseDto;
import com.example.cafemanagementsystem.dto.responce.SignUpResponseDto;

import javax.transaction.Transactional;
import java.nio.file.attribute.UserPrincipalNotFoundException;

public interface UserService {



    @Transactional
    SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) throws UserPrincipalNotFoundException;

    SignInResponseDto signIn(String username, String password) throws UserPrincipalNotFoundException;


}
