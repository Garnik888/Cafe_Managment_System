package com.example.cafemanagementsystem.dto.responce;

import com.example.cafemanagementsystem.domain.enums.AssortmentType;

import java.util.Objects;


public class AssortmentResponseDto {

    private String name;
    private AssortmentType assortmentType;

    private Double price;

    public AssortmentResponseDto() {
    }

    public AssortmentResponseDto(String name, AssortmentType assortmentType, Double price) {
        this.name = name;
        this.assortmentType = assortmentType;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public AssortmentType getAssortmentType() {
        return assortmentType;
    }

    public void setAssortmentType(AssortmentType assortmentType) {
        this.assortmentType = assortmentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssortmentResponseDto that = (AssortmentResponseDto) o;
        return  Objects.equals(name, that.name) && assortmentType == that.assortmentType && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, assortmentType, price);
    }

}
