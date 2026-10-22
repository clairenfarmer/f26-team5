package com.csc340.hobbycircles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.csc340.hobbycircles.entity.HobbyGroup;
import com.csc340.hobbycircles.entity.Interest;


public interface HobbyGroupRepository extends JpaRepository<HobbyGroup, Long> {
    boolean existsByEmailIgnoreCase(String email);

    List<HobbyGroup> findByInterestContainingIgnoreCase(Interest category);

    List<HobbyGroup> findMatchingCategory(@Param("category") Interest category);
}
