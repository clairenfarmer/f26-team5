package com.csc340.hobbycircles.service;

import com.csc340.hobbycircles.dto.IndividualResponse;
import com.csc340.hobbycircles.dto.IndividualRegistrationRequest;
import com.csc340.hobbycircles.dto.IndividualUpdateRequest;
import com.csc340.hobbycircles.entity.Individual;
import com.csc340.hobbycircles.repository.IndividualRepository;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class IndividualService {
    private final IndividualRepository individualRepository;

    public IndividualService(IndividualRepository individualRepository) {
        this.individualRepository = individualRepository;
    }

    public IndividualResponse register(IndividualRegistrationRequest request) {
        ensureEmailAvailable(request.email());
        Individual individual = new Individual();
        individual.setName(request.name().trim());
        individual.setEmail(request.email().trim().toLowerCase(Locale.ROOT));
        individual.setPassword(request.password());
        individual.setInterest(request.interest());
        return IndividualResponse.from(individualRepository.save(individual));
    }

    @Transactional(readOnly = true)
    public IndividualResponse getProfile(Long individualId) {
        return IndividualResponse.from(findIndividual(individualId));
    }

    public IndividualResponse updateProfile(Long individualId, IndividualUpdateRequest request) {
        Individual individual = findIndividual(individualId);
        if (!individual.getEmail().equalsIgnoreCase(request.email())
                && individualRepository.existsByEmailIgnoreCase(request.email())) {
            throw new ConflictException("An account with that email already exists.");
        }
        individual.setName(request.name().trim());
        individual.setEmail(request.email().trim().toLowerCase(Locale.ROOT));
        individual.setInterest(request.interest());
        return IndividualResponse.from(individualRepository.save(individual));
    }

    private void ensureEmailAvailable(String email) {
        if (individualRepository.existsByEmailIgnoreCase(email)) {
            throw new ConflictException("An account with that email already exists.");
        }
    }

    private Individual findIndividual(Long individualId) {
        return individualRepository.findById(individualId)
                .orElseThrow(() -> new NotFoundException("Individual " + individualId + " was not found."));
    }
    
}
