package com.example.cafemanagementsystem.service.impl;

import com.example.cafemanagementsystem.domain.entity.CafeTable;
import com.example.cafemanagementsystem.domain.entity.Order;
import com.example.cafemanagementsystem.domain.enums.OrderStatus;
import com.example.cafemanagementsystem.dto.request.OrderRequestDto;
import com.example.cafemanagementsystem.dto.responce.OrderResponseDto;
import com.example.cafemanagementsystem.repository.AssortmentOrderRepository;
import com.example.cafemanagementsystem.repository.CafeTableRepository;
import com.example.cafemanagementsystem.repository.OrderRepository;
import com.example.cafemanagementsystem.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CafeTableRepository cafeTableRepository;
    private final AssortmentOrderRepository assortmentOrderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, CafeTableRepository cafeTableRepository, AssortmentOrderRepository assortmentOrderRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.cafeTableRepository = cafeTableRepository;
        this.assortmentOrderRepository = assortmentOrderRepository;
    }


    @Override
    public OrderResponseDto createOrder(Long tableId, OrderRequestDto orderRequestDto) throws UserPrincipalNotFoundException {
        Optional<CafeTable> cafeTable = Optional.ofNullable(cafeTableRepository.findById(tableId).orElseThrow(() -> new UserPrincipalNotFoundException(String.format("Table with id %s is not found", tableId))));
        Order order = new Order();
        order.setOrderStatus(OrderStatus.OPEN);
        order.setDateTime(LocalDateTime.now());

        CafeTable cafeTable1 = modelMapper.map(cafeTable, CafeTable.class);
        order.setCafeTable(cafeTable1);
        orderRepository.save(order);
        OrderResponseDto orderResponseDto = modelMapper.map(order, OrderResponseDto.class);
        return orderResponseDto;
    }

    @Override
    public OrderResponseDto update(Long id) throws UserPrincipalNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() -> new UserPrincipalNotFoundException(String.format("Order with id %s is not found", id)));

            order.setOrderStatus(OrderStatus.CLOSED);
        Order save = orderRepository.save(order);
        return modelMapper.map(save, OrderResponseDto.class);
    }

    @Override
    public OrderResponseDto updateAndDelete(Long id) throws UserPrincipalNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new UserPrincipalNotFoundException(String.format("Order with id %s is not found", id)));
        assortmentOrderRepository.deleteAllByOrder(order.getId());

        order.setOrderStatus(OrderStatus.CANCELED);
        Order save = orderRepository.save(order);
        return modelMapper.map(save, OrderResponseDto.class);
    }


    @Override
    public OrderResponseDto findById(Long tableId) throws UserPrincipalNotFoundException {

        CafeTable cafeTable = cafeTableRepository.findById(tableId).orElseThrow(() ->
                new UserPrincipalNotFoundException(String.format("Table with id %s is not found", tableId)));
        Order order = orderRepository.findOrderByCafeTable(cafeTable);
        OrderResponseDto orderResponseDto = modelMapper.map(order, OrderResponseDto.class);
        return orderResponseDto;
    }
}





