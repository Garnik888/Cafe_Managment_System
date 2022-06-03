package com.example.cafemanagementsystem.domain.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import javax.persistence.Table;

@Entity
@Table(name = "cafe_table")
public class CafeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_name",unique = true,nullable = false)
    private String name;

    @Column(name = "reserve",nullable = false)
    private  boolean reserve;

    @OneToMany(
            mappedBy = "cafeTable",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    private List<Order> orders;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


    public CafeTable() {

    }

    public CafeTable(Long id, String name, boolean reserve, List<Order> orders, User user) {
        this.id = id;
        this.name = name;
        this.reserve = reserve;
        this.orders = orders;
        this.user = user;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeTable cafeTable = (CafeTable) o;
        return reserve == cafeTable.reserve && Objects.equals(id, cafeTable.id) && Objects.equals(name, cafeTable.name) && Objects.equals(orders, cafeTable.orders) && Objects.equals(user, cafeTable.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, reserve, orders, user);
    }

    @Override
    public String toString() {
        return "TableCafe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reserve=" + reserve +
                ", orders=" + orders +
                ", user=" + user +
                '}';
    }
}
