package com.example.cafemanagementsystem.service;


import com.example.cafemanagementsystem.dto.request.CafeTableRequestDto;
import com.example.cafemanagementsystem.dto.responce.CafeTableResponseDto;

import java.util.List;

public interface CafeTableService {

    CafeTableResponseDto createCafeTable(Long userId, CafeTableRequestDto cafeTableRequestDto) ;

    CafeTableResponseDto deleteById(Long id) ;

    List<CafeTableResponseDto> getAllFreeTables();

    List<CafeTableResponseDto> getTableByWaiterId(Long userId) ;
    CafeTableResponseDto updateName(String name, String newName) ;

    CafeTableResponseDto updateWaiter(Long id, Long userId) ;
}
