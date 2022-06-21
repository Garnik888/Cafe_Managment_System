package com.example.cafemanagementsystem.dto.responce;

import com.example.cafemanagementsystem.domain.enums.OrderStatus;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class OrderResponseDto {

    private Long id;
    private OrderStatus status;
    private Date date;


    public OrderResponseDto() {
    }

    public OrderResponseDto(Long id, OrderStatus status,
                            Date date) {
        this.id = id;
        this.status = status;
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderResponseDto that = (OrderResponseDto) o;
        return Objects.equals(id, that.id) && status == that.status && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, date);
    }

    @Override
    public String toString() {
        return "OrderResponseDto{" +
                "status=" + status +
                '}';
    }
}
