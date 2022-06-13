package com.example.cafemanagementsystem.service;

import com.example.cafemanagementsystem.dto.responce.OrderResponseDto;

import java.nio.file.attribute.UserPrincipalNotFoundException;

public interface OrderService {

    OrderResponseDto createOrder(Long tableId) throws UserPrincipalNotFoundException;

    OrderResponseDto update(Long id) throws UserPrincipalNotFoundException;

    OrderResponseDto updateAndDelete(Long id) throws UserPrincipalNotFoundException;

    OrderResponseDto findById(Long tableId) throws UserPrincipalNotFoundException;
}
