package com.example.cafemanagementsystem.controller;


import com.example.cafemanagementsystem.dto.responce.OrderResponseDto;
import com.example.cafemanagementsystem.exception.ApiRequestException;
import com.example.cafemanagementsystem.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Create New Order", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('WAITER')")
    public ResponseEntity<OrderResponseDto> save(@PathVariable("id") Long tableId)  {

        OrderResponseDto orderResponseDto = null;
        try {
            orderResponseDto = orderService.createOrder(tableId);
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (orderResponseDto == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(orderResponseDto);
    }

    @Operation(summary = "Update Order ", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/close/{id}")
    @PreAuthorize("hasAuthority('WAITER')")
    public ResponseEntity<OrderResponseDto> update(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(orderService.update(id));
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Operation(summary = "Delete  order", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/cancel/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<OrderResponseDto> delete(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(orderService.delete(id));
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);
        }
    }

    @Operation(summary = "Get Order for table id", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('WAITER')")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable("id") Long Id) {
        try {
            return ResponseEntity.ok(orderService.findById(Id));
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);
        }
    }

    @Operation(summary = "Get Orders by all waiter", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/date")
    public ResponseEntity<?> getOrdersByAllWaiter(@RequestParam("date") Date date) {

        Map<String, Integer> orderMapByUser = orderService.getOrdersByAllWaiter(date);

        if(orderMapByUser.isEmpty()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(orderMapByUser);
    }

    @Operation(summary = "Get Orders in data", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/all/date")

    public ResponseEntity<?> findAllOrderByData(@RequestParam("date") Date date) {

        List<OrderResponseDto> responseDtoList = orderService.findAllOrderByData(date);

        if(responseDtoList.isEmpty()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(responseDtoList);
    }
}
