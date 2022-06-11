package com.example.cafemanagementsystem.domain.entity;

import com.example.cafemanagementsystem.domain.enums.AssortmentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
    private AssortmentStatus assortmentStatus;
    @Column(name = "count",nullable = false)
    private Integer count;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assortment_id",nullable = false)
    private Assortment assortment;

    public AssortmentOrder() {
    }

    public AssortmentOrder(Long id, AssortmentStatus assortmentStatus,
                           Integer count, Order order,
                           Assortment assortment) {
        this.id = id;
        this.assortmentStatus = assortmentStatus;
        this.count = count;
        this.order = order;
        this.assortment = assortment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AssortmentStatus getAssortmentStatus() {
        return assortmentStatus;
    }

    public void setAssortmentStatus(AssortmentStatus assortmentStatus) {
        this.assortmentStatus = assortmentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssortmentOrder that = (AssortmentOrder) o;
        return Objects.equals(id, that.id)  && Objects.equals(count, that.count) && Objects.equals(order, that.order) && Objects.equals(assortment, that.assortment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count, order, assortment);
    }

    @Override
    public String toString() {
        return "AssortmentOrder{" +
                "id=" + id +
                ", assortmentStatus=" + assortmentStatus +
                ", count=" + count +
                '}';
    }
}
