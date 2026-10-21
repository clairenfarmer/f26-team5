package com.csc340.hobbycircles.repository;

import com.csc340.hobbycircles.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualRepository extends JpaRepository<Individual, Long> {
    boolean existsByEmailIgnoreCase(String email);
} 
