package com.example.cafemanagementsystem.service;

import com.example.cafemanagementsystem.dto.responce.AssortmentOrderResponseDto;

import java.util.List;

public interface AssortmentOrderService {


    /**
     * Create assortment order.
     *
     * @param orderId an uniq id whit help of it finding order in database.
     * @param assortmentId an uniq id whit help of it finding assortment in database.
     * @param count input assortment count.
     * @return a assortment order which user has set.
     */
    AssortmentOrderResponseDto createAssortmentOrder(Long orderId, Long assortmentId, Integer count);

    /**
     *Find assortment order by id and delete.
     *
     * @param id an uniq id whit help of it finding assortment order in database.
     * @return delete assortment order from database.
     */
    AssortmentOrderResponseDto updateStatusAndDelete(Long id);

    /**
     * Update count assortment order
     *
     * @param id – an uniq id whit help of it finding assortment order in database.
     * @param count new count for assortment order
     * @return update count assortment order
     */
    AssortmentOrderResponseDto updateCount(Long id, Integer count);

    /**
     * Assortment orders assigned to given order id.
     *
     * @param orderId -  an uniq id whit help of it finding order in database.
     * @return a List containing assortment orders assigned to given order id.
     */
    List<AssortmentOrderResponseDto> getByOrder(Long orderId);
}
