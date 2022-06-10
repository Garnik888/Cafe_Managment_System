package com.example.cafemanagementsystem.domain.entity;

import com.example.cafemanagementsystem.domain.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active = false;

    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;


    @JsonManagedReference
    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true
    )
    private List<CafeTable> tables;


    public User(Long id, String firstName,
                String lastName,String username, String password, Boolean active,
                RoleType roleType, List<CafeTable> tables) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.active = active;
        this.roleType = roleType;
        this.tables = tables;
    }

    public User(Long id, String firstName,
                String lastName,
                String username, String password,
                Boolean active, RoleType roleType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.active = active;
        this.roleType = roleType;
    }

    public User() {
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    public List<CafeTable> getTables() {
        return tables;
    }

    public void setTables(List<CafeTable> tables) {
        this.tables = tables;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName)  && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(active, user.active) && roleType == user.roleType && Objects.equals(tables, user.tables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName,username, password, active, roleType, tables);
    }
}
