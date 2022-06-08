package com.example.cafemanagementsystem.controller;


import com.example.cafemanagementsystem.service.CafeTableService;
import com.example.cafemanagementsystem.service.impl.CafeTableServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/coffee-tables")
public class CafeTableController {

    private final CafeTableService cafeTableService;

    @Autowired
    public CafeTableController(CafeTableServiceImpl cafeTableService) {
        this.cafeTableService = cafeTableService;
    }




}
