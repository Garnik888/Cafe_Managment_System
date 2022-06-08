package com.example.cafemanagementsystem.controller;


import com.example.cafemanagementsystem.domain.entity.CafeTable;
import com.example.cafemanagementsystem.dto.request.CafeTableRequestDto;
import com.example.cafemanagementsystem.dto.request.UserRequestDto;
import com.example.cafemanagementsystem.dto.responce.CafeTableResponseDto;
import com.example.cafemanagementsystem.service.CafeTableService;
import com.example.cafemanagementsystem.service.impl.CafeTableServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/coffee-tables")
public class CafeTableController {

    private final CafeTableServiceImpl cafeTableService;

    @Autowired
    public CafeTableController(CafeTableServiceImpl cafeTableService) {

        this.cafeTableService = cafeTableService;
    }

    @PostMapping
    public ResponseEntity<?> saveCafeTable(@RequestBody CafeTableRequestDto cafeTableRequestDto) throws Exception {

        CafeTableResponseDto cafeTableResponseDto = cafeTableService.createCafeTable(cafeTableRequestDto);

        if(cafeTableResponseDto != null) {

            return ResponseEntity.ok(cafeTableResponseDto);
        }

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Cafe table is not save");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCafeTable(@PathVariable("id") Long id) throws Exception {

        CafeTableResponseDto cafeTableResponseDto = cafeTableService.deleteById(id);

        if(cafeTableResponseDto != null) {

            return ResponseEntity.ok(cafeTableResponseDto);
        }

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cafe table not find");
    }

    @GetMapping("/freeTable")
    public ResponseEntity<?> getAllFreeTables() {

        List<CafeTableResponseDto> cafeTableResponseDtos = cafeTableService.getAllFreeTables();

        if(!cafeTableResponseDtos.isEmpty()) {

            return ResponseEntity.ok(cafeTableResponseDtos);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not find eny free table");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTableByWaiterId(@PathVariable("id") Long id) throws Exception {

        List<CafeTableResponseDto> cafeTableResponseDtos = cafeTableService.getTableByWaiterId(id);

        if(!cafeTableResponseDtos.isEmpty()) {

            return ResponseEntity.ok(cafeTableResponseDtos);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not find eny free table");
    }

    @PutMapping
    public ResponseEntity<?> updateName(@RequestBody String name, @RequestBody String newName) throws Exception {

        CafeTableResponseDto cafeTableResponseDto = cafeTableService.updateName(name, newName);

        if(cafeTableResponseDto != null) {

            return ResponseEntity.ok(cafeTableResponseDto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not find eny free table");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> upDateWaiter(@PathVariable("id") Long id,
                                          @RequestBody UserRequestDto userRequestDto) throws Exception {

        CafeTableResponseDto cafeTableResponseDto = cafeTableService.updateWaiter(id, userRequestDto);

        if(cafeTableResponseDto != null) {

            return ResponseEntity.ok(cafeTableResponseDto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not find eny free table");
    }
}
