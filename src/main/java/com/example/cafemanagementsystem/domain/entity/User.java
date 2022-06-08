package com.example.cafemanagementsystem.domain.entity;

import com.example.cafemanagementsystem.domain.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
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

    @Column(name = "email")
    @Email
    private  String email;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active = false;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole userRoleType;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true
    )
    private List<CafeTable> tables;

    public User() {
    }

    public User(String firstName,
                String lastName,
                String email,
                String username,
                String password,
                Boolean active,
                UserRole userRoleType,
                List<CafeTable> tables) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
        this.userRoleType = userRoleType;
        this.tables = tables;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getUserRoleType() {
        return userRoleType;
    }

    public void setUserRoleType(UserRole userRoleType) {
        this.userRoleType = userRoleType;
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

    public UserRole getRoleType() {
        return userRoleType;
    }

    public void setRoleType(UserRole userRoleType) {
        this.userRoleType = userRoleType;
    }

    public List<CafeTable> getTables() {
        return tables;
    }

    public void setTables(List<CafeTable> tables) {
        this.tables = tables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(active, user.active) && userRoleType == user.userRoleType && Objects.equals(tables, user.tables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, username, password, active, userRoleType, tables);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", userRoleType=" + userRoleType +
                '}';
    }
}
