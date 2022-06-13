package com.example.cafemanagementsystem.service.impl;

import com.example.cafemanagementsystem.domain.entity.CafeTable;
import com.example.cafemanagementsystem.domain.entity.User;
import com.example.cafemanagementsystem.domain.enums.RoleType;
import com.example.cafemanagementsystem.dto.request.CafeTableRequestDto;
import com.example.cafemanagementsystem.dto.request.UserRequestDto;
import com.example.cafemanagementsystem.dto.responce.CafeTableResponseDto;
import com.example.cafemanagementsystem.repository.CafeTableRepository;
import com.example.cafemanagementsystem.repository.UserRepository;
import com.example.cafemanagementsystem.service.CafeTableService;
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
    public CafeTableResponseDto createCafeTable(Long userId, CafeTableRequestDto cafeTableRequestDto) throws Exception {

        CafeTable cafeTable = modelMapper.map(cafeTableRequestDto, CafeTable.class);

        if (cafeTable == null) {

            throw new Exception("Cafe table is null");
        }

        Optional<User> getUser = userRepository.findById(userId);

        if (getUser.isEmpty()) {

            throw new Exception("Not found user");
        }

       User user= modelMapper.map(getUser, User.class);

        if(!user.getRoleType().equals(RoleType.WAITER)) {

            throw new Exception("Invalid role type");
        }

        cafeTable.setUser(user);


        return modelMapper.map(cafeTableRepository.save(cafeTable), CafeTableResponseDto.class);
    }

    @Override
    public CafeTableResponseDto deleteById(Long id) throws Exception {

        Optional<CafeTable> cafeTable = cafeTableRepository.findById(id);

        if (cafeTable.isEmpty()) {

            throw new Exception("Cafe table not found");
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
    public List<CafeTableResponseDto> getTableByWaiterId(Long userId) throws Exception {

        User user = modelMapper.map(userRepository.findById(userId), User.class);

        if (user == null) {

            throw new Exception("Not found user");
        }

        return cafeTableRepository.findCafeTableByUser(user);
    }

    @Override
    public CafeTableResponseDto updateName(String name, String newName) throws Exception {

        Optional<CafeTable> getCafeTable = cafeTableRepository.findByTableName(name);

        if (getCafeTable.isEmpty()) {

            throw new Exception("Not found cafe table");
        }

        CafeTable cafeTable = modelMapper.map(getCafeTable, CafeTable.class);

        cafeTable.setTableName(newName);
        CafeTable save = cafeTableRepository.save(cafeTable);
        return modelMapper.map(save, CafeTableResponseDto.class);
    }

    @Override
    public CafeTableResponseDto updateWaiter(Long id, UserRequestDto userRequestDto) throws Exception {

        Optional<CafeTable> getCafeTable = cafeTableRepository.findById(id);

        if (getCafeTable.isEmpty()) {

            throw new Exception("Not fund cafe table");
        }

        CafeTable cafeTable = modelMapper.map(getCafeTable, CafeTable.class);

        Optional<User> getUser = userRepository.findByUsername(userRequestDto.getUserName());

        if (getUser.isEmpty()) {

            throw new Exception("Not fund user");
        }

        cafeTable.setUser(modelMapper.map(getUser, User.class));

        return modelMapper.map(cafeTableRepository.save(cafeTable), CafeTableResponseDto.class);

    }
}
