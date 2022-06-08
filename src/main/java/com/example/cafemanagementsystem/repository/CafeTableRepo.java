package com.example.cafemanagementsystem.repository;

import com.example.cafemanagementsystem.domain.entity.CafeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CafeTableRepo extends JpaRepository<CafeTable,Long> {
}
