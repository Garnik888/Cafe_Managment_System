package com.example.cafemanagementsystem.repository;

import com.example.cafemanagementsystem.domain.entity.CafeTable;
import com.example.cafemanagementsystem.domain.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CafeTableRepository extends JpaRepository<CafeTable,Long> {

    List<CafeTable> findCafeTableByUser(User user);

    @Query("select c from CafeTable c where c.tableName = ?1")
    Optional<CafeTable> findByTableName(String name);
}
