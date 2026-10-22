package com.csc340.hobbycircles.dto;

import com.csc340.hobbycircles.entity.Individual;
import com.csc340.hobbycircles.entity.Interest;

public record IndividualResponse(
        Long id,
        String name, 
        String email, 
        Interest interest) {

    public static IndividualResponse from(Individual individual) {
        return new IndividualResponse(individual.getId(), individual.getName(), individual.getEmail(), individual.getInterest());
    }

}
