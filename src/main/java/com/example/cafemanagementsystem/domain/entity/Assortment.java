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
    private AssortmentType assortment;
    @Column(name = "price", nullable = false)
    private Double price;

    public Assortment() {
    }

    public Assortment(Long id, String name, AssortmentType assortment, Double price) {
        this.id = id;
        this.name = name;
        this.assortment = assortment;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assortment assortment = (Assortment) o;
        return Objects.equals(id, assortment.id) && Objects.equals(name, assortment.name) && Objects.equals(price, assortment.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
