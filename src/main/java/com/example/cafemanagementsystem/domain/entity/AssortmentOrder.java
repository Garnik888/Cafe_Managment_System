package com.example.cafemanagementsystem.domain.entity;

import com.example.cafemanagementsystem.domain.enums.ProductInOrderStatus;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "assortment_order")
public class AssortmentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductInOrderStatus status;

    @Column(name = "count",nullable = false)
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assortment_id",nullable = false)
    private Assortment assortment;

    public AssortmentOrder() {
    }

    public AssortmentOrder(Long id,
                           ProductInOrderStatus status,
                           Integer count,
                           Order order,
                           Assortment assortment) {
        this.id = id;
        this.status = status;
        this.count = count;
        this.order = order;
        this.assortment = assortment;
    }

    public AssortmentOrder(Long id, ProductInOrderStatus status, Integer count) {
        this.id = id;
        this.status = status;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductInOrderStatus getStatus() {
        return status;
    }

    public void setStatus(ProductInOrderStatus status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Assortment getAssortment() {
        return assortment;
    }

    public void setAssortment(Assortment assortment) {
        this.assortment = assortment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssortmentOrder that = (AssortmentOrder) o;
        return Objects.equals(id, that.id) && status == that.status && Objects.equals(count, that.count) && Objects.equals(order, that.order) && Objects.equals(assortment, that.assortment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, count, order, assortment);
    }

    @Override
    public String toString() {
        return "AssortmentOrder{" +
                "id=" + id +
                ", status=" + status +
                ", count=" + count +
                ", order=" + order +
                ", assortment=" + assortment +
                '}';
    }
}
