package com.example.cafemanagementsystem.repository;

import com.example.cafemanagementsystem.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.dateTime = ?1")
    List<Order> findAllByDateTime(LocalDate localDate);
}
