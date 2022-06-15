package com.example.cafemanagementsystem.domain.entity;


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


    public Assortment() {
    }

    public Assortment(Long id,
                      String name,
                      AssortmentType assortmentType,
                      Double price) {
        this.id = id;
        this.name = name;
        this.assortmentType = assortmentType;
        this.price = price;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assortment that = (Assortment) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && assortmentType == that.assortmentType && Objects.equals(price, that.price) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, assortmentType, price);
    }

}
