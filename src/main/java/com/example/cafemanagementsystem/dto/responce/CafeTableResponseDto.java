package com.example.cafemanagementsystem.dto.responce;

import com.example.cafemanagementsystem.domain.entity.User;
import com.example.cafemanagementsystem.dto.request.CafeTableRequestDto;

import java.util.Objects;

public class CafeTableResponseDto {

    private String tableName;
    private Boolean reserve;

    public CafeTableResponseDto() {
    }

    public CafeTableResponseDto(String tableName, Boolean reserve) {
        this.tableName = tableName;
        this.reserve = reserve;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Boolean getReserve() {
        return reserve;
    }

    public void setReserve(Boolean reserve) {
        this.reserve = reserve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeTableResponseDto that = (CafeTableResponseDto) o;
        return Objects.equals(tableName, that.tableName) && Objects.equals(reserve, that.reserve);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, reserve);
    }

    @Override
    public String toString() {
        return "CafeTableRequestDto{" +
                "tableName='" + tableName + '\'' +
                ", reserve=" + reserve +
                '}';
    }
}
