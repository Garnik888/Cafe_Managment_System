package com.example.cafemanagementsystem.dto.responce;

import com.example.cafemanagementsystem.domain.entity.Assortment;
import com.example.cafemanagementsystem.domain.enums.AssortmentStatus;

public class AssortmentOrderResponseDto {

    private AssortmentStatus assortmentStatus;
    private Integer count;


    public AssortmentOrderResponseDto() {
    }

    public AssortmentOrderResponseDto(AssortmentStatus assortmentStatus, Integer count) {
        this.assortmentStatus = assortmentStatus;
        this.count = count;

    }

    public AssortmentStatus getAssortmentStatus() {
        return assortmentStatus;
    }

    public void setAssortmentStatus(AssortmentStatus assortmentStatus) {
        this.assortmentStatus = assortmentStatus;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "AssortmentOrderResponseDto{" +
                "assortmentStatus=" + assortmentStatus +
                ", count=" + count +
                ", assortment=" +
                '}';
    }
}
