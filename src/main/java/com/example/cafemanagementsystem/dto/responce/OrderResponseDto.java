package com.example.cafemanagementsystem.dto.responce;

import com.example.cafemanagementsystem.domain.entity.CafeTable;
import com.example.cafemanagementsystem.domain.enums.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class OrderResponseDto {

    private OrderStatus status;
    private LocalDateTime dateTime;
    private CafeTable cafeTable;

    public OrderResponseDto() {
    }

    public OrderResponseDto(OrderStatus status, CafeTable cafeTable) {
        this.status = status;
        this.cafeTable = cafeTable;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public CafeTable getCafeTable() {
        return cafeTable;
    }

    public void setCafeTable(CafeTable cafeTable) {
        this.cafeTable = cafeTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderResponseDto that = (OrderResponseDto) o;
        return status == that.status && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, dateTime);
    }

    @Override
    public String toString() {
        return "OrderResponseDto{" +
                "status=" + status +
                ", dateTime=" + dateTime +
                '}';
    }
}
