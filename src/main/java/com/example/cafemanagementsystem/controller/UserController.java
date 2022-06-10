package com.example.cafemanagementsystem.controller;

import com.example.cafemanagementsystem.dto.request.SignInRequestDto;
import com.example.cafemanagementsystem.dto.request.SignUpRequestDto;
import com.example.cafemanagementsystem.dto.responce.SignInResponseDto;
import com.example.cafemanagementsystem.dto.responce.SignUpResponseDto;
import com.example.cafemanagementsystem.exception.ApiRequestException;
import com.example.cafemanagementsystem.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {


    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @Operation(summary = "Add a new user", description = "", tags = {"user"}, security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {

        SignUpResponseDto signUpResponseDto = null;
        try {
            signUpResponseDto = userService.signUp(signUpRequestDto);
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);
        }
        if (signUpResponseDto == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(signUpResponseDto);
    }


    @PostMapping("/signIn")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        SignInResponseDto signInResponseDto = null;
        try {
            signInResponseDto = userService.signIn(signInRequestDto.getUsername(), signInRequestDto.getPassword());
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);
        }
        if (signInResponseDto ==null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(signInResponseDto);
    }
}

