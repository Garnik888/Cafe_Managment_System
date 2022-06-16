package com.example.cafemanagementsystem.validator;

import com.example.cafemanagementsystem.domain.entity.Order;
import com.example.cafemanagementsystem.domain.enums.OrderStatus;

public class OrderValidator {

    public static boolean isOrderStatusOpen(Order order) {
        return order.getOrderStatus().equals(OrderStatus.CLOSED);
    }
}
