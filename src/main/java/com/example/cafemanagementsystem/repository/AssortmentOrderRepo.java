package com.example.cafemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssortmentOrderRepo  extends JpaRepository<AssortmentRepo,Long> {

}
