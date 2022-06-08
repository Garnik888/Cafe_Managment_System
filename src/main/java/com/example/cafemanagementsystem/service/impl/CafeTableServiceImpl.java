package com.example.cafemanagementsystem.service.impl;

import com.example.cafemanagementsystem.domain.entity.CafeTable;
import com.example.cafemanagementsystem.domain.entity.User;
import com.example.cafemanagementsystem.dto.request.CafeTableRequestDto;
import com.example.cafemanagementsystem.dto.responce.CafeTableResponseDto;
import com.example.cafemanagementsystem.repository.CafeTableRepo;
import com.example.cafemanagementsystem.repository.UserRepo;
import com.example.cafemanagementsystem.service.CafeTableService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


    @Transactional
    @Service
    public class CafeTableServiceImpl implements CafeTableService {

        private final ModelMapper modelMapper;
        private final CafeTableRepo coffeeTableRepository;
        private final UserRepo userRepository;

        @Autowired
        public CafeTableServiceImpl(ModelMapper modelMapper,
                                    CafeTableRepo coffeeTableRepository,
                                    UserRepo userRepository) {
            this.modelMapper = modelMapper;
            this.coffeeTableRepository = coffeeTableRepository;
            this.userRepository = userRepository;
        }



    }
