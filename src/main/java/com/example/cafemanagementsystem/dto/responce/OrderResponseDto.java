package com.example.cafemanagementsystem.dto.responce;

import com.example.cafemanagementsystem.domain.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderResponseDto {

    private Long id;
    private OrderStatus status;
    private LocalDateTime dateTime;


    public OrderResponseDto() {
    }

    public OrderResponseDto(Long id, OrderStatus status, LocalDateTime dateTime) {
        this.id = id;
        this.status = status;
        this.dateTime = dateTime;
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
