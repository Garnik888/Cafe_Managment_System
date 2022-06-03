package com.example.cafemanagementsystem.repository;

import com.example.cafemanagementsystem.domain.entity.Assortment;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssortmentRepo extends JpaRepository<Assortment, Long> {


}
