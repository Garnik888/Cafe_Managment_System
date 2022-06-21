package com.example.cafemanagementsystem.dto.responce;

import com.example.cafemanagementsystem.domain.entity.Assortment;
import com.example.cafemanagementsystem.domain.enums.AssortmentStatus;

public class AssortmentOrderResponseDto {

    private AssortmentStatus assortmentStatus;
    private Integer count;

    private Assortment assortment;



    public AssortmentOrderResponseDto() {
    }

    public AssortmentOrderResponseDto(AssortmentStatus assortmentStatus,
                                      Integer count, Assortment assortment) {
        this.assortmentStatus = assortmentStatus;
        this.count = count;
        this.assortment = assortment;
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

    public Assortment getAssortment() {
        return assortment;
    }

    public void setAssortment(Assortment assortment) {
        this.assortment = assortment;
    }

    @Override
    public String toString() {
        return "AssortmentOrderResponseDto{" +
                "assortmentStatus=" + assortmentStatus +
                ", count=" + count +
                ", assortment=" + assortment +
                '}';
    }
}
