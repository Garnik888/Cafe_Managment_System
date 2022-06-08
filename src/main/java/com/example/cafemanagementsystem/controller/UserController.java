package com.example.cafemanagementsystem.controller;

import com.example.cafemanagementsystem.dto.request.SignInRequestDto;
import com.example.cafemanagementsystem.dto.request.SignUpRequestDto;
import com.example.cafemanagementsystem.dto.responce.SignInResponseDto;
import com.example.cafemanagementsystem.dto.responce.SignUpResponseDto;
import com.example.cafemanagementsystem.service.UserService;
import com.example.cafemanagementsystem.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {


        private final UserServiceImpl userService;

        public UserController(UserServiceImpl userService) {
            this.userService = userService;
        }


        @Operation(summary = "Add a new user", description="", tags = {"user"}, security = @SecurityRequirement(name = "bearerAuth"))
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "User created",
                        content = @Content(schema = @Schema(implementation = SignUpRequestDto.class))),
                @ApiResponse(responseCode = "409", description = "User already exists")})
       // @PreAuthorize("hasAnyAuthority('admin')")
        @PostMapping("/signUp")
        @ResponseStatus(HttpStatus.CREATED)
        public SignUpResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
          return  userService.signUp(signUpRequestDto);
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

