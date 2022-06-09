package com.example.cafemanagementsystem.service.impl;

import com.example.cafemanagementsystem.domain.entity.CafeTable;
import com.example.cafemanagementsystem.domain.entity.User;
import com.example.cafemanagementsystem.dto.request.CafeTableRequestDto;
import com.example.cafemanagementsystem.dto.request.UserRequestDto;
import com.example.cafemanagementsystem.dto.responce.CafeTableResponseDto;
import com.example.cafemanagementsystem.repository.CafeTableRepo;
import com.example.cafemanagementsystem.repository.UserRepo;
import com.example.cafemanagementsystem.service.CafeTableService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Transactional
    @Service
    public class CafeTableServiceImpl implements CafeTableService {

        private final ModelMapper modelMapper;
        private final CafeTableRepo cafeTableRepo;
        private final UserRepo userRepository;

        @Autowired
        public CafeTableServiceImpl(ModelMapper modelMapper,
                                    CafeTableRepo cafeTableRepo,
                                    UserRepo userRepository) {
            this.modelMapper = modelMapper;
            this.cafeTableRepo = cafeTableRepo;
            this.userRepository = userRepository;
        }


            public CafeTableResponseDto createCafeTable(Long userId, CafeTableRequestDto cafeTableRequestDto) throws Exception {

                CafeTable cafeTable = modelMapper.map(cafeTableRequestDto, CafeTable.class);

                if(cafeTable == null) {

                    throw new Exception("Cafe table is null");
                }

                Optional<User> getUser = userRepository.findById(userId);

                if(getUser.isEmpty()) {

                    throw new Exception("Not find user");
                }

                cafeTable.setUser(modelMapper.map(getUser, User.class));

                return modelMapper.map(cafeTableRepo.save(cafeTable), CafeTableResponseDto.class);
            }

        public CafeTableResponseDto deleteById(Long id) throws Exception {

            Optional<CafeTable> cafeTable = cafeTableRepo.findById(id);

            if(cafeTable.isEmpty()) {

                throw new Exception("Cafe table not find");
            }

            CafeTableResponseDto cafeTableResponseDto = modelMapper.map(cafeTable, CafeTableResponseDto.class);

            cafeTableRepo.deleteById(id);

            return cafeTableResponseDto;
        }

        public List<CafeTableResponseDto> getAllFreeTables() {

            List<CafeTableResponseDto> cafeTableResponseDtos = new ArrayList<>();
            List<CafeTable> cafeTables = cafeTableRepo.findAll();

            for (CafeTable cafeTable : cafeTables) {

                if(!cafeTable.isReserve()) {
                    cafeTableResponseDtos.add(modelMapper.map(cafeTable,
                            CafeTableResponseDto.class));
                }
            }

            return cafeTableResponseDtos;
        }

        public List<CafeTableResponseDto> getTableByWaiterId(Long userId) throws Exception {

            User user = modelMapper.map(userRepository.findById(userId), User.class);

            if(user == null) {

               throw new Exception("Not fund user");
            }

            return cafeTableRepo.findCafeTableByUser(user);
        }

        public CafeTableResponseDto updateName(String name, String newName) throws Exception {

            Optional<CafeTable> getCafeTable =cafeTableRepo.findByTableName(name);

            if(getCafeTable.isEmpty()) {

                throw new Exception("Not fund cafe table");
            }

            CafeTable cafeTable = modelMapper.map(getCafeTable, CafeTable.class);

            cafeTable.setTableName(newName);

            return modelMapper.map(cafeTableRepo.save(cafeTable), CafeTableResponseDto.class);
        }

        public CafeTableResponseDto updateWaiter(Long id, UserRequestDto userRequestDto) throws Exception {

            Optional<CafeTable> getCafeTable =cafeTableRepo.findById(id);

            if(getCafeTable.isEmpty()) {

                throw new Exception("Not fund cafe table");
            }

            CafeTable cafeTable = modelMapper.map(getCafeTable, CafeTable.class);

            Optional<User> getUser = userRepository.findByEmail(userRequestDto.getEmail());

            if(getUser.isEmpty()) {

                throw new Exception("Not fund user");
            }

            cafeTable.setUser(modelMapper.map(getUser, User.class));

            return modelMapper.map(cafeTableRepo.save(cafeTable), CafeTableResponseDto.class);

        }
    }
