package com.csc340.hobbycircles.dto;

import java.util.Set;

import com.csc340.hobbycircles.entity.Individual;
import com.csc340.hobbycircles.entity.Interest;

public record IndividualResponse(
        Long id,
        String name, 
        String email, 
        Set<Interest> interests) {

    public static IndividualResponse from(Individual individual) {
        return new IndividualResponse(individual.getId(), individual.getName(), individual.getEmail(), individual.getInterests());
    }

}
