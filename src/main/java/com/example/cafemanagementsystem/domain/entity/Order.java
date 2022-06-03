package com.example.cafemanagementsystem.domain.entity;

import com.example.cafemanagementsystem.domain.enums.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "data_time")
    private LocalDateTime dateTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id",nullable = false)
    private CafeTable cafeTable;

    @OneToMany(
            mappedBy = "order",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AssortmentOrder> assortmentOrderList;

    public Order() {

        this.dateTime = LocalDateTime.now();
    }


    public Order(Long id,
                 OrderStatus status,
                 LocalDateTime dateTime,
                 CafeTable cafeTable,
                 List<AssortmentOrder> assortmentOrderList) {
        this.id = id;
        this.status = status;
        this.dateTime = dateTime;
        this.cafeTable = cafeTable;
        this.assortmentOrderList = assortmentOrderList;
    }


    public Order(Long id, OrderStatus status, LocalDateTime dateTime, CafeTable cafeTable) {
        this.id = id;
        this.status = status;
        this.dateTime = dateTime;
        this.cafeTable = cafeTable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public CafeTable getTableCafe() {
        return cafeTable;
    }

    public void setTableCafe(CafeTable cafeTable) {
        this.cafeTable = cafeTable;
    }

    public List<AssortmentOrder> getProductInOrderList() {
        return assortmentOrderList;
    }

    public void setProductInOrderList(List<AssortmentOrder> assortmentOrderList) {
        this.assortmentOrderList = assortmentOrderList;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && status == order.status && Objects.equals(dateTime, order.dateTime) && Objects.equals(cafeTable, order.cafeTable) && Objects.equals(assortmentOrderList, order.assortmentOrderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, dateTime, cafeTable, assortmentOrderList);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", dateTime=" + dateTime +
                ", tableCafe=" + cafeTable +
                ", productInOrderList=" + assortmentOrderList +
                '}';
    }
}
