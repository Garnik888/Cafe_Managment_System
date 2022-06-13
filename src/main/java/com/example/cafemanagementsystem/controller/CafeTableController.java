package com.example.cafemanagementsystem.controller;


import com.example.cafemanagementsystem.dto.request.CafeTableRequestDto;
import com.example.cafemanagementsystem.dto.request.UserRequestDto;
import com.example.cafemanagementsystem.dto.responce.CafeTableResponseDto;
import com.example.cafemanagementsystem.service.impl.CafeTableServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cafe-table")
public class CafeTableController {

    private final CafeTableServiceImpl cafeTableService;

    @Autowired
    public CafeTableController(CafeTableServiceImpl cafeTableService) {

        this.cafeTableService = cafeTableService;
    }


    @Operation(summary = "Save cafe table",  security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> saveCafeTable(@PathVariable("id") Long userId,
                                           @RequestBody CafeTableRequestDto cafeTableRequestDto) throws Exception {

        CafeTableResponseDto cafeTableResponseDto = cafeTableService.createCafeTable(userId, cafeTableRequestDto);

        if(cafeTableResponseDto != null) {

            return ResponseEntity.ok(cafeTableResponseDto);
        }

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Cafe table  not save");
    }

    @Operation(summary = "Delete cafe table",  security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> deleteCafeTable(@PathVariable("id") Long id) throws Exception {

        CafeTableResponseDto cafeTableResponseDto = cafeTableService.deleteById(id);

        if(cafeTableResponseDto != null) {

            return ResponseEntity.ok(cafeTableResponseDto);
        }

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cafe table not find");
    }

    @Operation(summary = "Get all free table",  security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/freeTable")
    @PreAuthorize("hasAnyAuthority('MANAGER','WAITER')")
    public ResponseEntity<?> getAllFreeTables() {

        List<CafeTableResponseDto> cafeTableResponseDtos = cafeTableService.getAllFreeTables();

        if(!cafeTableResponseDtos.isEmpty()) {

            return ResponseEntity.ok(cafeTableResponseDtos);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not find any free table");
    }

    @Operation(summary = "Get table by waiter id",  security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> getTableByWaiterId(@PathVariable("id") Long id) throws Exception {

        List<CafeTableResponseDto> cafeTableResponseDtos = cafeTableService.getTableByWaiterId(id);

        if(!cafeTableResponseDtos.isEmpty()) {

            return ResponseEntity.ok(cafeTableResponseDtos);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found any table");
    }

    @Operation(summary = "Update name",  security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{tableName}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> updateName(@PathVariable("tableName") String name,@RequestBody String newName) throws Exception {

        CafeTableResponseDto cafeTableResponseDto = cafeTableService.updateName(name,newName);

        if(cafeTableResponseDto != null) {

            return ResponseEntity.ok(cafeTableResponseDto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found any free table");
    }

    @Operation(summary = "Update waiter",  security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> upDateWaiter(@PathVariable("id") Long id,
                                          @RequestBody UserRequestDto userRequestDto) throws Exception {

        CafeTableResponseDto cafeTableResponseDto = cafeTableService.updateWaiter(id, userRequestDto);

        if(cafeTableResponseDto != null) {

            return ResponseEntity.ok(cafeTableResponseDto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found any free table");
    }
}
