package com.example.cafemanagementsystem.controller;


import com.example.cafemanagementsystem.domain.enums.OrderStatus;
import com.example.cafemanagementsystem.dto.request.CafeTableRequestDto;
import com.example.cafemanagementsystem.dto.request.OrderRequestDto;
import com.example.cafemanagementsystem.dto.responce.CafeTableResponseDto;
import com.example.cafemanagementsystem.dto.responce.OrderResponseDto;
import com.example.cafemanagementsystem.exception.ApiRequestException;
import com.example.cafemanagementsystem.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Create New Order",  security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/{id}")
    public ResponseEntity<OrderResponseDto> save(@RequestBody OrderRequestDto orderRequestDto,
                                                   @PathVariable("id") Long tableId) {

        OrderResponseDto  orderResponseDto=null;
        try {
            orderResponseDto = orderService.createOrder(tableId,orderRequestDto);
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);
        }
        if (orderResponseDto == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(orderResponseDto);
    }

    @Operation(summary = "Update Order and delete assortment order",  security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/close/{id}")
    public ResponseEntity<OrderResponseDto> update(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(orderService.update(id));
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);}}


    @Operation(summary = "Update Order and delete assortment order",  security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/cancel/{id}")
    public ResponseEntity<OrderResponseDto> updateAndDelete(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(orderService.updateAndDelete(id));
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);}}

    @Operation(summary = "Get Order",  security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable("id") Long tableId) {
        try {
            return ResponseEntity.ok(orderService.findById(tableId));
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);
        }
    }


}
