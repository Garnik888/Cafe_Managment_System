package com.example.cafemanagementsystem.service;

import com.example.cafemanagementsystem.dto.responce.OrderResponseDto;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.Map;

public interface OrderService {

    OrderResponseDto createOrder(Long tableId) throws Exception;

    OrderResponseDto update(Long id) throws Exception;

    OrderResponseDto delete(Long id) throws UserPrincipalNotFoundException;

    OrderResponseDto findById(Long tableId) throws UserPrincipalNotFoundException;

    public Map<String, Integer> getOrdersByAllWaiter(LocalDate localDate);
}
