package com.example.cafemanagementsystem.repository;

import com.example.cafemanagementsystem.domain.entity.Assortment;
import com.example.cafemanagementsystem.domain.entity.AssortmentOrder;
import com.example.cafemanagementsystem.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssortmentOrderRepository extends JpaRepository<AssortmentOrder, Long> {
    void deleteAllByOrder(Long orderId);

    void deleteAllByOrder(Order order);

    List<AssortmentOrder> findAllByOrder(Order order);

    Optional<AssortmentOrder> findByOrderAndAssortment(Order order, Assortment assortment);

}
