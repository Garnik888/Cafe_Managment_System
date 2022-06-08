package com.example.cafemanagementsystem.domain.entity;

import com.example.cafemanagementsystem.domain.enums.AssortmentStatus;
import com.example.cafemanagementsystem.domain.enums.AssortmentType;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "assortment")
public class Assortment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assortment_name", unique = true,nullable = false)
    private String name;

    @Column(name = "assortment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AssortmentType assortmentType;
    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private AssortmentStatus assortmentStatus;

    public Assortment() {
    }

    public Assortment(Long id,
                      String name,
                      AssortmentType assortmentType,
                      Double price,
                      AssortmentStatus assortmentStatus) {
        this.id = id;
        this.name = name;
        this.assortmentType = assortmentType;
        this.price = price;
        this.assortmentStatus = assortmentStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public AssortmentType getAssortmentType() {
        return assortmentType;
    }

    public void setAssortmentType(AssortmentType assortmentType) {
        this.assortmentType = assortmentType;
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
        Assortment that = (Assortment) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && assortmentType == that.assortmentType && Objects.equals(price, that.price) && assortmentStatus == that.assortmentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, assortmentType, price, assortmentStatus);
    }


    @Override
    public String toString() {
        return "Assortment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", assortmentType=" + assortmentType +
                ", price=" + price +
                ", assortmentStatus=" + assortmentStatus +
                '}';
    }
}
