package com.example.cafemanagementsystem.service.impl;

import com.example.cafemanagementsystem.domain.entity.Assortment;
import com.example.cafemanagementsystem.domain.enums.AssortmentType;
import com.example.cafemanagementsystem.dto.request.AssortmentRequestDto;
import com.example.cafemanagementsystem.dto.responce.AssortmentResponseDto;
import com.example.cafemanagementsystem.repository.AssortmentRepository;
import com.example.cafemanagementsystem.service.AssortmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssortmentServiceImpl implements AssortmentService {
    private final ModelMapper modelMapper;
    private final AssortmentRepository assortmentRepository;

    @Autowired
    public AssortmentServiceImpl(ModelMapper modelMapper, AssortmentRepository assortmentRepository) {
        this.modelMapper = modelMapper;
        this.assortmentRepository = assortmentRepository;
    }

    @Override
    public AssortmentResponseDto createAssortment(AssortmentRequestDto assortmentRequestDto) {
        Assortment assortment = modelMapper.map(assortmentRequestDto, Assortment.class);

        return modelMapper.map(assortmentRepository.save(assortment), AssortmentResponseDto.class);
    }

    @Override
    public AssortmentResponseDto deleteById(Long id) {

        Optional<Assortment> getAssortment = assortmentRepository.findById(id);

        if (getAssortment.isEmpty()) {

            throw new RuntimeException("Assortment not found");
        }

        AssortmentResponseDto assortmentResponseDto = modelMapper.map(getAssortment, AssortmentResponseDto.class);

        assortmentRepository.deleteById(id);

        return assortmentResponseDto;
    }


    @Override
    public AssortmentResponseDto updateByPrice(String name, Double price) {

        Optional<Assortment> getAssortment = assortmentRepository.findByName(name);

        if (getAssortment.isEmpty()) {

            throw new RuntimeException("Assortment not found");
        }

        Assortment assortment = modelMapper.map(getAssortment, Assortment.class);

        assortment.setPrice(price);

        return modelMapper.map(assortmentRepository.save(assortment), AssortmentResponseDto.class);
    }

    @Override
    public List<AssortmentResponseDto> getAllFood() {

        List<AssortmentResponseDto> assortmentResponseDtos = new ArrayList<>();

        List<Assortment> assortments = assortmentRepository.findAllByAssortmentType(AssortmentType.FOOD);

        for (Assortment assortment : assortments) {

            assortmentResponseDtos.add(modelMapper.map(assortment, AssortmentResponseDto.class));
        }

        return assortmentResponseDtos;
    }


    @Override
    public List<AssortmentResponseDto> getAllDrink() {

        List<AssortmentResponseDto> assortmentResponseDtos = new ArrayList<>();

        List<Assortment> assortments = assortmentRepository.findAllByAssortmentType(AssortmentType.DRINK);

        for (Assortment assortment : assortments) {

            assortmentResponseDtos.add(modelMapper.map(assortment, AssortmentResponseDto.class));
        }

        return assortmentResponseDtos;
    }
}
