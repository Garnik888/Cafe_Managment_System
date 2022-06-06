package com.example.cafemanagementsystem.service;

import com.example.cafemanagementsystem.dto.request.AssortmentRequestDto;
import com.example.cafemanagementsystem.dto.responce.AssortmentResponseDto;

import java.util.List;

public interface AssortmentService {
    AssortmentResponseDto createProduct(AssortmentRequestDto assortmentRequestDto);

    List<AssortmentResponseDto> getProducts();
}
