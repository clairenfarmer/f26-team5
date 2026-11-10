package com.csc340.hobbycircles.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.csc340.hobbycircles.entity.HobbyGroup;
import com.csc340.hobbycircles.entity.Interest;


public interface HobbyGroupRepository extends JpaRepository<HobbyGroup, Long> {
    boolean existsByEmailIgnoreCase(String email);

    Optional<HobbyGroup> findByEmailIgnoreCase(String email);

    @Query("SELECT h FROM HobbyGroup h WHERE h.interest = :category")
    List<HobbyGroup> findMatchingCategory(@Param("category") Interest category);
}
