package com.example.cafemanagementsystem.validator;

import com.example.cafemanagementsystem.domain.entity.AssortmentOrder;
import com.example.cafemanagementsystem.domain.entity.Order;
import com.example.cafemanagementsystem.domain.enums.AssortmentStatus;
import com.example.cafemanagementsystem.domain.enums.OrderStatus;

public class AssortmentOrderValidator {

    public static boolean isOrderOpen(Order order) {

        return order.getOrderStatus().equals(OrderStatus.OPEN);
    }

    public static boolean isAssortmentOrderCancelled(AssortmentOrder assortmentOrder) {

        return assortmentOrder.getAssortmentStatus().equals(AssortmentStatus.CANCELLED);
    }
}
