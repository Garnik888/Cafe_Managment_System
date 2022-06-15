package com.example.cafemanagementsystem.controller;

import com.example.cafemanagementsystem.dto.responce.AssortmentOrderResponseDto;
import com.example.cafemanagementsystem.service.impl.AssortmentOrderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assortment-order")
public class AssortmentOrderController {

    private final AssortmentOrderServiceImpl assortmentOrderService;

    public AssortmentOrderController(AssortmentOrderServiceImpl assortmentOrderService) {
        this.assortmentOrderService = assortmentOrderService;
    }

    @Operation(summary = "Save assortment order", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('WAITER')")
    public ResponseEntity<?> saveAssortmentOrder(@PathVariable("id") Long orderId,
                                                 @RequestBody Long assortmentId, Integer count) {

        AssortmentOrderResponseDto assortmentOrderResponseDto =
                assortmentOrderService.createAssortmentOrder(orderId, assortmentId, count);

        if (assortmentOrderResponseDto != null) {

            return ResponseEntity.ok(assortmentOrderResponseDto);
        }

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Can not save assortment order");
    }


    @Operation(summary = "Order  delete",
            security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("cancelled/{id}")
    @PreAuthorize("hasAuthority('WAITER')")

    public ResponseEntity<?> updateStatusAndDelete(@PathVariable("id") Long id) {

        AssortmentOrderResponseDto assortmentOrderResponseDto =
                assortmentOrderService.updateStatusAndDelete(id);

        if (assortmentOrderResponseDto != null) {

            return ResponseEntity.ok(assortmentOrderResponseDto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not found assortment order");

    }

    @Operation(summary = "Update assortment order count",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("count/{id}")
    @PreAuthorize("hasAuthority('WAITER')")
    public ResponseEntity<?> updateCount(@PathVariable("id") Long id,
                                         @RequestBody Integer count) {
        AssortmentOrderResponseDto assortmentOrderResponseDto =
                assortmentOrderService.updateCount(id, count);

        if (assortmentOrderResponseDto != null) {

            return ResponseEntity.ok(assortmentOrderResponseDto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not found assortment order");
    }


    @Operation(summary = "Get all assortment order by order",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER','WAITER')")
    public ResponseEntity<?> getByOrder(@PathVariable("id") Long orderId) {

        List<AssortmentOrderResponseDto> assortmentOrderResponseDtoList =
                assortmentOrderService.getByOrder(orderId);

        if (assortmentOrderResponseDtoList != null) {

            return ResponseEntity.ok(assortmentOrderResponseDtoList);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not found assortment order");
    }
}
