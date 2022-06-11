package com.example.cafemanagementsystem.service;


import com.example.cafemanagementsystem.dto.request.AssortmentRequestDto;
import com.example.cafemanagementsystem.dto.responce.AssortmentResponseDto;

import java.util.List;

public interface AssortmentService {


    AssortmentResponseDto createAssortment(AssortmentRequestDto assortmentRequestDto) throws Exception;

    AssortmentResponseDto deleteById(Long id) throws Exception;

    AssortmentResponseDto updateByPrice(String name, Double price) throws Exception;

    List<AssortmentResponseDto> getAllFood();

    List<AssortmentResponseDto> getAllDrink();
}
