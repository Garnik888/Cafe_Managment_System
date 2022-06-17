package com.example.cafemanagementsystem.service.impl;

import com.example.cafemanagementsystem.domain.entity.Assortment;
import com.example.cafemanagementsystem.domain.entity.AssortmentOrder;
import com.example.cafemanagementsystem.domain.entity.Order;
import com.example.cafemanagementsystem.domain.enums.AssortmentStatus;
import com.example.cafemanagementsystem.dto.responce.AssortmentOrderResponseDto;
import com.example.cafemanagementsystem.repository.AssortmentOrderRepository;
import com.example.cafemanagementsystem.repository.AssortmentRepository;
import com.example.cafemanagementsystem.repository.OrderRepository;
import com.example.cafemanagementsystem.service.AssortmentOrderService;
import com.example.cafemanagementsystem.validator.AssortmentOrderValidator;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssortmentOrderServiceImpl implements AssortmentOrderService {
    private final AssortmentOrderRepository assortmentOrderRepo;
    private final OrderRepository orderRepo;
    private final AssortmentRepository assortmentRepo;
    private final ModelMapper modelMapper;

    public AssortmentOrderServiceImpl(AssortmentOrderRepository assortmentOrderRepo,
                                      OrderRepository orderRepo,
                                      AssortmentRepository assortmentRepo,
                                      ModelMapper modelMapper) {
        this.assortmentOrderRepo = assortmentOrderRepo;
        this.orderRepo = orderRepo;
        this.assortmentRepo = assortmentRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public AssortmentOrderResponseDto createAssortmentOrder(Long orderId,
                                                            Long assortmentId,
                                                            Integer count) {

        Order order = orderRepo.findById(orderId).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Order with id %s is not found", orderId)));

        if (!AssortmentOrderValidator.isOrderOpen(order)) {

            throw new RuntimeException("Order is not open");
        }

        Assortment assortment = assortmentRepo.findById(assortmentId).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Assortment with is %s is not found", assortmentId)));

        Optional<AssortmentOrder> getAssortmentOrder = assortmentOrderRepo.findByOrderAndAssortment(order, assortment);

        if (getAssortmentOrder.isPresent()) {

            AssortmentOrder assortmentOrder = getAssortmentOrder.get();
            assortmentOrder.setCount(assortmentOrder.getCount() + count);

            return modelMapper.map(assortmentOrderRepo.save(assortmentOrder), AssortmentOrderResponseDto.class);
        }

        AssortmentOrder newAssortmentOrder = new AssortmentOrder();
        newAssortmentOrder.setAssortmentStatus(AssortmentStatus.ACTIVE);
        newAssortmentOrder.setOrder(order);
        newAssortmentOrder.setAssortment(assortment);
        newAssortmentOrder.setCount(count);
        AssortmentOrder save = assortmentOrderRepo.save(newAssortmentOrder);
        return modelMapper.map(save, AssortmentOrderResponseDto.class);
    }

    @Override
    public AssortmentOrderResponseDto deleteAssortmentOrder(Long id) {

        AssortmentOrderResponseDto assortmentOrderResponseDto;

        AssortmentOrder assortmentOrder = assortmentOrderRepo.findById(id).orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("Assortment order by id %s not found", id))
        );

        assortmentOrder.setAssortmentStatus(AssortmentStatus.CANCELLED);

        assortmentOrderResponseDto = modelMapper.map(assortmentOrder, AssortmentOrderResponseDto.class);

        assortmentOrderRepo.delete(assortmentOrder);

        return assortmentOrderResponseDto;
    }

    @Override
    public AssortmentOrderResponseDto updateCount(Long id, Integer count) {

        AssortmentOrder assortmentOrder = assortmentOrderRepo.findById(id).orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("Assortment order by id %s not found", id))
        );

        Order order = orderRepo.findById(assortmentOrder.getOrder().getId()).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Order with id %s is not found",
                        assortmentOrder.getOrder().getId())));

        if (!AssortmentOrderValidator.isOrderOpen(order)) {

            throw new RuntimeException("Order is not open");
        }

        if(AssortmentOrderValidator.isAssortmentOrderCancelled(assortmentOrder)) {

            throw new RuntimeException("Assortment order cancelled");
        }

        assortmentOrder.setCount(count);

        return modelMapper.map(assortmentOrderRepo.save(assortmentOrder), AssortmentOrderResponseDto.class);
    }

    @Override
    public AssortmentOrderResponseDto updateAssortmentStatus(Long id) {
        AssortmentOrder assortmentOrder = assortmentOrderRepo.findById(id).orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("Assortment order by id %s not found", id))
        );

        assortmentOrder.setAssortmentStatus(AssortmentStatus.CANCELLED);
        return modelMapper.map(assortmentOrderRepo.save(assortmentOrder), AssortmentOrderResponseDto.class);
    }

    @Override
    public List<AssortmentOrderResponseDto> getByOrder(Long orderId) {

        List<AssortmentOrderResponseDto> assortmentOrderResponseDtoList = new ArrayList<>();

        Optional<Order> order = orderRepo.findById(orderId);

        List<AssortmentOrder> assortmentOrders = assortmentOrderRepo.findAllByOrder(
                modelMapper.map(order, Order.class)
        );

        for (AssortmentOrder assortmentOrder : assortmentOrders) {

            assortmentOrderResponseDtoList.add(
                    modelMapper.map(assortmentOrder, AssortmentOrderResponseDto.class));

        }

        return assortmentOrderResponseDtoList;
    }
}
