package com.example.cafemanagementsystem.repository;

import com.example.cafemanagementsystem.domain.entity.AssortmentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssortmentOrderRepo  extends JpaRepository<AssortmentOrder,Long> {

}
