package com.example.cafemanagementsystem.controller;

import com.example.cafemanagementsystem.dto.request.SignInRequestDto;
import com.example.cafemanagementsystem.dto.request.SignUpRequestDto;
import com.example.cafemanagementsystem.dto.responce.SignInResponseDto;
import com.example.cafemanagementsystem.dto.responce.SignUpResponseDto;

import com.example.cafemanagementsystem.exception.ApiRequestException;
import com.example.cafemanagementsystem.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {


        private final UserServiceImpl userService;

        public UserController(UserServiceImpl userService) {
            this.userService = userService;
        }


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



        @Operation(summary = "User signIn", description = "", tags = {"user"})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "User signin",
                        content = @Content(schema = @Schema(implementation = SignInRequestDto.class))),
                @ApiResponse(responseCode = "404", description = "Wrong username or password")})
      //  @PreAuthorize("hasAnyAuthority('admin')")
        @PostMapping("/signIn")
        public SignInResponseDto signIn(@RequestBody SignInRequestDto dto) {
            return userService.signIn(dto.getEmail(), dto.getPassword());
        }
    }

