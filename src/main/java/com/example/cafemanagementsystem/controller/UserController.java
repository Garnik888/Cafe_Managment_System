package com.example.cafemanagementsystem.controller;

import com.example.cafemanagementsystem.dto.request.SignInRequestDto;
import com.example.cafemanagementsystem.dto.request.SignUpRequestDto;
import com.example.cafemanagementsystem.dto.responce.SignInResponseDto;
import com.example.cafemanagementsystem.dto.responce.SignUpResponseDto;
import com.example.cafemanagementsystem.dto.responce.UserResponseDto;
import com.example.cafemanagementsystem.exception.ApiRequestException;
import com.example.cafemanagementsystem.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {


    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @Operation(summary = "Add a new user",  security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/signUp")
    @PreAuthorize("hasAuthority('ADMIN')")
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

    @Operation(summary = "Sign in user")
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



    @Operation(summary = "Delete user",  security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponseDto> delete(@PathVariable("username") String username) {
        try {
            return ResponseEntity.ok(userService.deleteUser(username));
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);}}

    @Operation(summary = "Update password",  security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponseDto> updatePassword(@PathVariable("username") String username,@RequestBody String newPassword) {
        try {
            return ResponseEntity.ok(userService.updatePassword(username,newPassword));
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);}}

    @Operation(summary = "Get user by id",  security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponseDto> findById(@PathVariable("id") Long userId) {
        try {
            return ResponseEntity.ok(userService.findById(userId));
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);
        }
    }

    @Operation(summary = "Get all waiters",  security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/waiter")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public ResponseEntity<List<UserResponseDto>> findByRoleTypeWaiter() {
        try {
            return ResponseEntity.ok(userService.findByRoleTypeWaiter());
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);
        }
    }


    @Operation(summary = "Get user",  security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> findByRoleTypeALL() {
        try {
            return ResponseEntity.ok(userService.findAll());
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);
        }
    }


}

