package com.example.cafemanagementsystem.repository;

import com.example.cafemanagementsystem.domain.entity.CafeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeTableRepo extends JpaRepository<CafeTable,Long> {

}
