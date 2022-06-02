package com.example.cafemanagementsystem.domain.entity;

import com.example.cafemanagementsystem.domain.enums.OrderStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "create_date")
    private LocalDate createDate;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableCafe tableCafe;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductInOrder> productInOrderList;

    public Order() {
    }

    public Order(Long id, OrderStatus status, LocalDate createDate, TableCafe tableCafe) {
        this.id = id;
        this.status = status;
        this.createDate = createDate;
        this.tableCafe = tableCafe;
    }

    public Order(Long id,
                 OrderStatus status, LocalDate createDate, TableCafe tableCafe,
                 List<ProductInOrder> productInOrderList) {
        this.id = id;
        this.status = status;
        this.createDate = createDate;
        this.tableCafe = tableCafe;
        this.productInOrderList = productInOrderList;
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

    public TableCafe getTableCafe() {
        return tableCafe;
    }

    public void setTableCafe(TableCafe tableCafe) {
        this.tableCafe = tableCafe;
    }

    public List<ProductInOrder> getProductInOrderList() {
        return productInOrderList;
    }

    public void setProductInOrderList(List<ProductInOrder> productInOrderList) {
        this.productInOrderList = productInOrderList;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && status == order.status && Objects.equals(createDate, order.createDate) && Objects.equals(tableCafe, order.tableCafe) && Objects.equals(productInOrderList, order.productInOrderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, createDate, tableCafe, productInOrderList);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", createDate=" + createDate +
                ", tableCafe=" + tableCafe +
                ", productInOrderList=" + productInOrderList +
                '}';
    }
}
