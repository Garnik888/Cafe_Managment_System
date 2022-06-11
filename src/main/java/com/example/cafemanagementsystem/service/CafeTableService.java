package com.example.cafemanagementsystem.service;


import com.example.cafemanagementsystem.dto.request.CafeTableRequestDto;
import com.example.cafemanagementsystem.dto.request.UserRequestDto;
import com.example.cafemanagementsystem.dto.responce.CafeTableResponseDto;

import java.util.List;

public interface CafeTableService {

    CafeTableResponseDto createCafeTable(Long userId, CafeTableRequestDto cafeTableRequestDto) throws Exception;

    CafeTableResponseDto deleteById(Long id) throws Exception;

    List<CafeTableResponseDto> getAllFreeTables();

    List<CafeTableResponseDto> getTableByWaiterId(Long userId) throws Exception;

    CafeTableResponseDto updateName(String name, String newName) throws Exception;

    CafeTableResponseDto updateWaiter(Long id, UserRequestDto userRequestDto) throws Exception;
}
