package com.example.cafemanagementsystem.service.impl;

import com.example.cafemanagementsystem.domain.entity.AssortmentOrder;
import com.example.cafemanagementsystem.domain.entity.CafeTable;
import com.example.cafemanagementsystem.domain.entity.Order;
import com.example.cafemanagementsystem.domain.enums.OrderStatus;
import com.example.cafemanagementsystem.dto.request.OrderRequestDto;
import com.example.cafemanagementsystem.dto.responce.OrderResponseDto;
import com.example.cafemanagementsystem.repository.AssortmentOrderRepo;
import com.example.cafemanagementsystem.repository.CafeTableRepo;
import com.example.cafemanagementsystem.repository.OrderRepo;
import com.example.cafemanagementsystem.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepository;
    private final ModelMapper modelMapper;
    private final CafeTableRepo cafeTableRepo;
    private final AssortmentOrderRepo assortmentOrderRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepository, ModelMapper modelMapper, CafeTableRepo cafeTableRepo, AssortmentOrderRepo assortmentOrderRepo) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.cafeTableRepo = cafeTableRepo;
        this.assortmentOrderRepo = assortmentOrderRepo;
    }


    @Override
    public OrderResponseDto createOrder(Long tableId, OrderRequestDto orderRequestDto) throws UserPrincipalNotFoundException {
        Optional<CafeTable> cafeTable = Optional.ofNullable(cafeTableRepo.findById(tableId).orElseThrow(() -> new UserPrincipalNotFoundException(String.format("Table with id %s is not found", tableId))));
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
        Order order = orderRepository.findById(id).orElseThrow(() -> new UserPrincipalNotFoundException(String.format("Order with id %s is not found", id)));
           assortmentOrderRepo.deleteAssortmentOrdersByOrder(order);

        Order save = orderRepository.save(order);
        return modelMapper.map(save, OrderResponseDto.class);
    }


    @Override
    public OrderResponseDto findById(Long tableId) throws UserPrincipalNotFoundException {

        CafeTable cafeTable = cafeTableRepo.findById(tableId).orElseThrow(() ->
                new UserPrincipalNotFoundException(String.format("Table with id %s is not found", tableId)));
        Order order = orderRepository.findOrderByCafeTable(cafeTable);
        OrderResponseDto orderResponseDto = modelMapper.map(order, OrderResponseDto.class);
        return orderResponseDto;
    }
}





