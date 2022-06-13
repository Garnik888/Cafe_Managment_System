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
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public AssortmentOrderResponseDto createAssortmentOrder(Long orderId, Long assortmentId, Integer count) {

        Order order = orderRepo.findById(orderId).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Order with id %s is not found", orderId)));

        Assortment assortment = assortmentRepo.findById(assortmentId).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Assortment with is %s is not found", assortmentId)));

        AssortmentOrder assortmentOrder = new AssortmentOrder();
        assortmentOrder.setAssortmentStatus(AssortmentStatus.ACTIVE);
        assortmentOrder.setOrder(order);
        assortmentOrder.setAssortment(assortment);
        assortmentOrder.setCount(count);
        AssortmentOrder save = assortmentOrderRepo.save(assortmentOrder);
        return modelMapper.map(save, AssortmentOrderResponseDto.class);
    }

    @Override
    public AssortmentOrderResponseDto updateStatusAndDelete(Long id) {

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

        assortmentOrder.setCount(count);

        return modelMapper.map(assortmentOrderRepo.save(assortmentOrder), AssortmentOrderResponseDto.class);
    }

    @Override
    public List<AssortmentOrderResponseDto> getByOrder(Long orderId) {

        List<AssortmentOrderResponseDto> assortmentOrderResponseDtoList = new ArrayList<>();

        List<AssortmentOrder> assortmentOrders = assortmentOrderRepo.findAllByOrder(orderId);

        for (AssortmentOrder assortmentOrder : assortmentOrders) {

            assortmentOrderResponseDtoList.add(
                    modelMapper.map(assortmentOrder, AssortmentOrderResponseDto.class));

        }

        return assortmentOrderResponseDtoList;
    }
}
