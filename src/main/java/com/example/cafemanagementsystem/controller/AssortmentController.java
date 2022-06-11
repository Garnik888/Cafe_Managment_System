package com.example.cafemanagementsystem.controller;

import com.example.cafemanagementsystem.dto.request.AssortmentRequestDto;
import com.example.cafemanagementsystem.dto.responce.AssortmentResponseDto;
import com.example.cafemanagementsystem.dto.responce.OrderResponseDto;
import com.example.cafemanagementsystem.exception.ApiRequestException;
import com.example.cafemanagementsystem.service.AssortmentService;
import com.example.cafemanagementsystem.service.impl.AssortmentServiceImpl;
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
import java.util.List;


@RestController
@RequestMapping("/api/assortment")
public class AssortmentController {
    private final AssortmentServiceImpl assortmentService;

    public AssortmentController(AssortmentServiceImpl assortmentService) {

        this.assortmentService = assortmentService;
    }

    @Operation(summary = "Create assortment", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping
    public ResponseEntity<AssortmentResponseDto> saveAssortment(@RequestBody AssortmentRequestDto assortmentRequestDto) throws Exception {
        AssortmentResponseDto assortmentResponseDto=null;
        try {
            assortmentResponseDto = assortmentService.createAssortment(assortmentRequestDto);
        } catch (UserPrincipalNotFoundException e) {
            String message = e.getName();
            throw new ApiRequestException(message);
        }
        if (assortmentResponseDto == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(assortmentResponseDto);
    }

    @Operation(summary = "Delete assortment", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAssortment(@PathVariable(name = "id") Long id) throws Exception {

        AssortmentResponseDto assortmentResponseDto = assortmentService.deleteById(id);

        if(assortmentResponseDto != null) {

            return ResponseEntity.ok(assortmentResponseDto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assortment not found");
    }

    @Operation(summary = "Update assortment price", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{name}")
    public ResponseEntity<?> updateByPrice(@PathVariable("name") String name,
                                           @RequestBody Double price) throws Exception {

        AssortmentResponseDto assortmentResponseDto = assortmentService.updateByPrice(name, price);

        if(assortmentResponseDto != null) {

            return ResponseEntity.ok(assortmentResponseDto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assortment not found");
    }

    @Operation(summary = "Get all assortment food", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/food")
    public ResponseEntity<?> getAllFood() {

        List<AssortmentResponseDto> assortmentResponseDtos = assortmentService.getAllFood();

        if(assortmentResponseDtos != null) {

            return ResponseEntity.ok(assortmentResponseDtos);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assortment not found");
    }

    @Operation(summary = "Get all assortment drink", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/drink")
    public ResponseEntity<?> getAllDrink() {

        List<AssortmentResponseDto> assortmentResponseDtos = assortmentService.getAllDrink();

        if(assortmentResponseDtos != null) {

            return ResponseEntity.ok(assortmentResponseDtos);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assortment not found");
    }
}