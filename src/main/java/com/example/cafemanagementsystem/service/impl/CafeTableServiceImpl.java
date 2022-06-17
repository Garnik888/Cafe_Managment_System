package com.example.cafemanagementsystem.service.impl;

import com.example.cafemanagementsystem.domain.entity.CafeTable;
import com.example.cafemanagementsystem.domain.entity.User;
import com.example.cafemanagementsystem.domain.enums.RoleType;
import com.example.cafemanagementsystem.dto.request.CafeTableRequestDto;
import com.example.cafemanagementsystem.dto.responce.CafeTableResponseDto;
import com.example.cafemanagementsystem.repository.CafeTableRepository;
import com.example.cafemanagementsystem.repository.UserRepository;
import com.example.cafemanagementsystem.service.CafeTableService;
import com.example.cafemanagementsystem.validator.CafeTableValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CafeTableServiceImpl implements CafeTableService {

    private final ModelMapper modelMapper;
    private final CafeTableRepository cafeTableRepository;
    private final UserRepository userRepository;


    @Autowired
    public CafeTableServiceImpl(ModelMapper modelMapper, CafeTableRepository cafeTableRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.cafeTableRepository = cafeTableRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CafeTableResponseDto createCafeTable(Long userId, CafeTableRequestDto cafeTableRequestDto) {

        CafeTable cafeTable = modelMapper.map(cafeTableRequestDto, CafeTable.class);

        if (cafeTable == null) {

            throw new RuntimeException("Cafe table is null");
        }

        Optional<User> getUser = userRepository.findById(userId);

        if (getUser.isEmpty()) {

            throw new RuntimeException("Not found user");
        }

        User user = modelMapper.map(getUser, User.class);

        if (CafeTableValidator.isUserWaiter(user)) {

            throw new RuntimeException("Invalid role type");
        }

        cafeTable.setUser(user);

        return modelMapper.map(cafeTableRepository.save(cafeTable), CafeTableResponseDto.class);
    }

    @Override
    public CafeTableResponseDto deleteById(Long id) {

        Optional<CafeTable> cafeTable = cafeTableRepository.findById(id);

        if (cafeTable.isEmpty()) {

            throw new RuntimeException("Cafe table not found");
        }

        CafeTableResponseDto cafeTableResponseDto = modelMapper.map(cafeTable, CafeTableResponseDto.class);

        cafeTableRepository.deleteById(id);

        return cafeTableResponseDto;
    }

    @Override
    public List<CafeTableResponseDto> getAllFreeTables() {

        List<CafeTableResponseDto> cafeTableResponseDtos = new ArrayList<>();
        List<CafeTable> cafeTables = cafeTableRepository.findAll();

        for (CafeTable cafeTable : cafeTables) {

            if (!cafeTable.isReserve()) {
                cafeTableResponseDtos.add(modelMapper.map(cafeTable, CafeTableResponseDto.class));
            }
        }

        return cafeTableResponseDtos;
    }

    @Override
    public List<CafeTableResponseDto> getTableByWaiterId(Long userId) {

        List<CafeTableResponseDto> cafeTableResponseDtoList = new ArrayList<>();

        Optional<User> getUser = userRepository.findById(userId);

        User user = modelMapper.map(getUser, User.class);

        if (getUser.isEmpty()) {

            throw new RuntimeException("Not found user");
        }

        List<CafeTable> cafeTables = cafeTableRepository.findCafeTableByUser(user);

        for (CafeTable cafeTable : cafeTables) {

            cafeTableResponseDtoList.add(modelMapper.map(cafeTable, CafeTableResponseDto.class));
        }

        return cafeTableResponseDtoList;
    }

    @Override
    public CafeTableResponseDto updateName(String name, String newName) {

        Optional<CafeTable> getCafeTable = cafeTableRepository.findByTableName(name);

        if (getCafeTable.isEmpty()) {

            throw new RuntimeException("Not found cafe table");
        }

        CafeTable cafeTable = modelMapper.map(getCafeTable, CafeTable.class);

        cafeTable.setTableName(newName);
        CafeTable save = cafeTableRepository.save(cafeTable);
        return modelMapper.map(save, CafeTableResponseDto.class);
    }

    @Override
    public CafeTableResponseDto updateWaiter(Long id, Long userId) {

        Optional<CafeTable> getCafeTable = cafeTableRepository.findById(id);

        if (getCafeTable.isEmpty()) {

            throw new RuntimeException("Not fund cafe table");
        }

        CafeTable cafeTable = modelMapper.map(getCafeTable, CafeTable.class);
        Optional<User> user = userRepository.findById(userId);
        User newUser = modelMapper.map(user, User.class);
        cafeTable.setUser(newUser);


        return modelMapper.map(cafeTableRepository.save(cafeTable), CafeTableResponseDto.class);

    }
}
