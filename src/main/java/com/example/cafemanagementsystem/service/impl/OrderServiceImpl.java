package com.example.cafemanagementsystem.service.impl;

import com.example.cafemanagementsystem.domain.entity.CafeTable;
import com.example.cafemanagementsystem.domain.entity.Order;
import com.example.cafemanagementsystem.domain.entity.User;
import com.example.cafemanagementsystem.domain.enums.OrderStatus;
import com.example.cafemanagementsystem.dto.responce.OrderResponseDto;
import com.example.cafemanagementsystem.dto.responce.UserResponseDto;
import com.example.cafemanagementsystem.repository.CafeTableRepository;
import com.example.cafemanagementsystem.repository.OrderRepository;
import com.example.cafemanagementsystem.repository.UserRepository;
import com.example.cafemanagementsystem.service.OrderService;
import com.example.cafemanagementsystem.validator.OrderValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CafeTableRepository cafeTableRepository;
    private final AssortmentOrderServiceImpl assortmentOrderService;
    private  final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            ModelMapper modelMapper,
                            CafeTableRepository cafeTableRepository,
                            AssortmentOrderServiceImpl assortmentOrderService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.cafeTableRepository = cafeTableRepository;
        this.assortmentOrderService = assortmentOrderService;
        this.userRepository = userRepository;
    }


    @Override
    public OrderResponseDto createOrder(Long tableId) throws Exception {
        Optional<CafeTable> getCafeTable = Optional.ofNullable(cafeTableRepository.findById(tableId).orElseThrow(()
                -> new UserPrincipalNotFoundException(String.format("Table with id %s is not found", tableId))));

        Order order = new Order();

        CafeTable cafeTable = modelMapper.map(getCafeTable, CafeTable.class);

        if (cafeTable.isReserve()) {
            throw new RuntimeException("Cafe table is reserved");
        }


        cafeTable.setReserve(true);
        cafeTableRepository.save(cafeTable);
        order.setOrderStatus(OrderStatus.OPEN);
        order.setDateTime(Date.valueOf(LocalDate.now()));

        CafeTable cafeTable1 = modelMapper.map(cafeTable, CafeTable.class);
        order.setCafeTable(cafeTable1);
        orderRepository.save(order);
        OrderResponseDto orderResponseDto = modelMapper.map(order, OrderResponseDto.class);
        return orderResponseDto;
    }

    @Override
    public OrderResponseDto update(Long id) throws UserPrincipalNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new UserPrincipalNotFoundException(String.format("Order with id %s is not found", id)));

        if (!order.getCafeTable().isReserve()) {
            throw new RuntimeException("Cafe table is reserved");
        }

        if(OrderValidator.isOrderStatusOpen(order)) {

            throw new RuntimeException("Order status is not OPEN you can not close");
        }
        order.setOrderStatus(OrderStatus.CLOSED);
        Order save = orderRepository.save(order);
        CafeTable cafeTable = cafeTableRepository.findById(save.getCafeTable().getId()).get();
        cafeTable.setReserve(false);
        cafeTableRepository.save(cafeTable);
        return modelMapper.map(save, OrderResponseDto.class);
    }

    @Override
    public OrderResponseDto delete(Long id) throws UserPrincipalNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new UserPrincipalNotFoundException(String.format("Order with id %s is not found", id)));

        if(!OrderValidator.haveOrderAssortment(id)){
                throw new RuntimeException("You cane not delete order, order have assortment");
        }

        CafeTable cafeTable = cafeTableRepository.findById(order.getTableCafe().getId()).get();
        cafeTable.setReserve(false);
        cafeTableRepository.save(cafeTable);
        orderRepository.delete(order);

        return modelMapper.map(id, OrderResponseDto.class);
    }

    @Override
    public OrderResponseDto findById(Long id) throws UserPrincipalNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new UserPrincipalNotFoundException(String.format("Order with id %s is not found", id)));
        OrderResponseDto orderResponseDto = modelMapper.map(order, OrderResponseDto.class);
        return orderResponseDto;
    }

    @Override
    public Map<String, Integer> getOrdersByAllWaiter(LocalDate localDate) {

        Map<String, Integer> usersMap = new HashMap<>();

        List<Order> orders = orderRepository.findAllByDateTime(localDate);

        for (Order order : orders) {

            String userName = order.getCafeTable().getUser().getUsername();

            if(usersMap.containsKey(userName)) {

                Integer count = usersMap.get(userName);
                count ++;

                usersMap.put(userName, count);
            }

            usersMap.put(userName, 1);
        }

        return usersMap;
    }


}
