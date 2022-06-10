package com.example.cafemanagementsystem.repository;

import com.example.cafemanagementsystem.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    @Query("select u from User u where u.username = ?1")
    Optional<User> findByUsername(String username);
}

