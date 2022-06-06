package com.example.cafemanagementsystem.service.impl;

import com.example.cafemanagementsystem.dto.request.AssortmentRequestDto;
import com.example.cafemanagementsystem.dto.responce.AssortmentResponseDto;
import com.example.cafemanagementsystem.service.AssortmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssortmentServiceImpl implements AssortmentService {


    @Override
    public AssortmentResponseDto createProduct(AssortmentRequestDto assortmentRequestDto) {
        return null;
    }

    @Override
    public List<AssortmentResponseDto> getProducts() {
        return null;
    }
}
