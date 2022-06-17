package com.example.cafemanagementsystem.validator;

import com.example.cafemanagementsystem.domain.entity.Order;
import com.example.cafemanagementsystem.domain.enums.OrderStatus;
import com.example.cafemanagementsystem.dto.responce.AssortmentOrderResponseDto;
import com.example.cafemanagementsystem.service.AssortmentOrderService;

import java.util.List;

public class OrderValidator {

    private static final AssortmentOrderService assortmentOrderService = null;


    public static boolean isOrderStatusOpen(Order order) {

        return order.getOrderStatus().equals(OrderStatus.CLOSED);
    }

    public static boolean haveOrderAssortment(Long id) {

        List<AssortmentOrderResponseDto> assortmentOrderResponseDtoList =
                assortmentOrderService.getByOrder(id);

        return assortmentOrderResponseDtoList.isEmpty();
    }
}
