package com.example.cafemanagementsystem.service;

import com.example.cafemanagementsystem.dto.responce.AssortmentOrderResponseDto;

import java.util.List;

public interface AssortmentOrderService {


    AssortmentOrderResponseDto createAssortmentOrder(Long orderId, Long assortmentId, Integer count);

    AssortmentOrderResponseDto updateStatusAndDelete(Long id);

    AssortmentOrderResponseDto updateCount(Long id, Integer count);

    List<AssortmentOrderResponseDto> getByOrder(Long orderId);
}
