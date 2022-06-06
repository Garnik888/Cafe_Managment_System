package com.example.cafemanagementsystem.controller;

import com.example.cafemanagementsystem.dto.request.SignInRequestDto;
import com.example.cafemanagementsystem.dto.request.SignUpRequestDto;
import com.example.cafemanagementsystem.dto.responce.SignInResponseDto;
import com.example.cafemanagementsystem.dto.responce.SignUpResponseDto;
import com.example.cafemanagementsystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {


        private final UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }


        @Operation(summary = "Add a new user", description="", tags = {"user"}, security = @SecurityRequirement(name = "bearerAuth"))
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "User created",
                        content = @Content(schema = @Schema(implementation = SignUpRequestDto.class))),
                @ApiResponse(responseCode = "409", description = "User already exists")})
        @PostMapping("/signup")
        @ResponseStatus(HttpStatus.CREATED)
        public SignUpResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
           SignUpResponseDto signUpResponseDto=userService.createUser(signUpRequestDto);
//                    signUpResponseDto.setFirstName(dto.getFirstName());
//                    signUpResponseDto.setLastName(dto.getLastName());
//                    signUpResponseDto.setUsername(dto.getUsername());

        return signUpResponseDto;
        }

        @Operation(summary = "User sigIn", description = "", tags = {"user"})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "User signin",
                        content = @Content(schema = @Schema(implementation = SignInRequestDto.class))),
                @ApiResponse(responseCode = "404", description = "Wrong username or password")})
        @PostMapping("/signIn")
        public SignInResponseDto signIn(@RequestBody SignInRequestDto dto) {
            return userService.signIn(dto.getEmail(), dto.getPassword());
        }
    }

