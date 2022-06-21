package com.example.cafemanagementsystem.domain.entity;

import com.example.cafemanagementsystem.domain.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
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
    private OrderStatus orderStatus;

    @Column(name = "data_time")
    private Date dateTime;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id",nullable = false)
    private CafeTable cafeTable;
    @JsonManagedReference
    @OneToMany(
            mappedBy = "order",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AssortmentOrder> assortmentOrderList;

    public Order() {

        this.dateTime = Date.valueOf(LocalDate.now());
    }


    public Order(Long id,
                 OrderStatus orderStatus,
               Date dateTime,
                 CafeTable cafeTable,
                 List<AssortmentOrder> assortmentOrderList) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.dateTime = dateTime;
        this.cafeTable = cafeTable;
        this.assortmentOrderList = assortmentOrderList;
    }

    public Order(Long id, OrderStatus orderStatus, Date dateTime) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public CafeTable getCafeTable() {
        return cafeTable;
    }

    public void setCafeTable(CafeTable cafeTable) {
        this.cafeTable = cafeTable;
    }

    public List<AssortmentOrder> getAssortmentOrderList() {
        return assortmentOrderList;
    }

    public void setAssortmentOrderList(List<AssortmentOrder> assortmentOrderList) {
        this.assortmentOrderList = assortmentOrderList;
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


    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && orderStatus == order.orderStatus && Objects.equals(dateTime, order.dateTime) && Objects.equals(cafeTable, order.cafeTable) && Objects.equals(assortmentOrderList, order.assortmentOrderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderStatus, dateTime, cafeTable, assortmentOrderList);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderStatus=" + orderStatus +
                ", dateTime=" + dateTime +
                ", cafeTable=" + cafeTable +
                ", assortmentOrderList=" + assortmentOrderList +
                '}';
    }
}
