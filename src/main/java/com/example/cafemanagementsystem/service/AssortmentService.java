package com.example.cafemanagementsystem.service;


import com.example.cafemanagementsystem.dto.request.AssortmentRequestDto;
import com.example.cafemanagementsystem.dto.responce.AssortmentResponseDto;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface AssortmentService {

    AssortmentResponseDto createAssortment(AssortmentRequestDto assortmentRequestDto);

    /**
     * Find assortment by id and delete.
     * @param id – an uniq id whit help of it finding assortment in database.
     * @return AssortmentResponseDto delete assortment from database.
     * @throws Exception if assortment not found
     */
    AssortmentResponseDto deleteById(Long id) throws Exception;

    /**
     * Update price and save assortment in database
     *
     * @param name – an uniq name whit help of it finding assortment in database.
     * @param price new price for assortment
     * @returna AssortmentResponseDto which has new price assortment stored in database.
     * @throws Exception if not found assortment
     */
    AssortmentResponseDto updateByPrice(String name, Double price) throws Exception;

    /**
     * Assortments assigned to given assortment type FOOD
     *
     * @return a List containing assortments assigned to given assortment type FOOD.
     */
    List<AssortmentResponseDto> getAllFood();

    /**
     * Assortments assigned to given assortment type DRINK
     *
     * @return a List containing assortments assigned to given assortment type DRINK.
     */
    List<AssortmentResponseDto> getAllDrink();
}
