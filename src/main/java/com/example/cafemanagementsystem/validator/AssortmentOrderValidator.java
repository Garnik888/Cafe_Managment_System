package com.example.cafemanagementsystem.validator;

import com.example.cafemanagementsystem.domain.entity.Order;
import com.example.cafemanagementsystem.domain.enums.OrderStatus;

public class AssortmentOrderValidator {

    public static boolean isOrderOpen(Order order) {

        return order.getOrderStatus().equals(OrderStatus.OPEN);
    }
}
