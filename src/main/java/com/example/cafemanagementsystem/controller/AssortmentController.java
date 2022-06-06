package com.example.cafemanagementsystem.controller;

import com.example.cafemanagementsystem.dto.request.AssortmentRequestDto;
import com.example.cafemanagementsystem.dto.responce.AssortmentResponseDto;
import com.example.cafemanagementsystem.service.AssortmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
public class AssortmentController {

    private final AssortmentService assortmentService;

    public AssortmentController(AssortmentService assortmentService) {
        this.assortmentService = assortmentService;
    }

    @Operation(summary = "Create new product", description = "", tags = {"product-management"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created",
                    content = @Content(schema = @Schema(implementation = AssortmentResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "Product already exists")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssortmentResponseDto createProduct(@RequestBody AssortmentRequestDto assortmentRequestDto) {
        return assortmentService.createProduct(assortmentRequestDto);
    }

    @Operation(summary = "Retrieve products", description = "", tags = {"product-management"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved",
                    content = @Content(schema = @Schema(implementation = AssortmentResponseDto.class)))})
    @GetMapping
    public List<AssortmentResponseDto> getProducts() {
        return assortmentService.getProducts();
    }
}
