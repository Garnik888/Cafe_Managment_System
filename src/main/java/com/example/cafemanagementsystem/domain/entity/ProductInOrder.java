package com.example.cafemanagementsystem.domain.entity;

import com.example.cafemanagementsystem.domain.enums.ProductInOrderStatus;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "product_in_order")
public class ProductInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductInOrderStatus status;

    @Column(name = "count")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductInOrder() {
    }

    public ProductInOrder(Long id, ProductInOrderStatus status, Integer count, Order order, Product product) {
        this.id = id;
        this.status = status;
        this.count = count;
        this.order = order;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrder that = (ProductInOrder) o;
        return Objects.equals(id, that.id) && status == that.status && Objects.equals(count, that.count) && Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, count, order, product);
    }

    @Override
    public String toString() {
        return "ProductInOrder{" +
                "id=" + id +
                ", status=" + status +
                ", count=" + count +
                ", order=" + order +
                ", product=" + product +
                '}';
    }
}
