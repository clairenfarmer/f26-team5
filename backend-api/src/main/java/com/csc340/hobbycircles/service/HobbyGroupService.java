package com.csc340.hobbycircles.service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csc340.hobbycircles.dto.HobbyGroupRegistrationRequest;
import com.csc340.hobbycircles.dto.HobbyGroupResponse;
import com.csc340.hobbycircles.dto.HobbyGroupUpdateRequest;
import com.csc340.hobbycircles.entity.HobbyGroup;
import com.csc340.hobbycircles.entity.Interest;
import com.csc340.hobbycircles.repository.HobbyGroupRepository;


@Service
@Transactional
public class HobbyGroupService {
    private final HobbyGroupRepository hobbyGroupRepository;

    public HobbyGroupService(HobbyGroupRepository hobbyGroupRepository) {
        this.hobbyGroupRepository = hobbyGroupRepository;
    }

    public HobbyGroupResponse register(HobbyGroupRegistrationRequest request) {
       ensureEmailAvailable(request.email());
       if (request.password().getBytes(StandardCharsets.UTF_8).length > 72) {
            throw new BadRequestException("Password must be no more than 72 UTF-8 bytes.");
        }
       
        HobbyGroup hobbyGroup = new HobbyGroup();
        hobbyGroup.setName(request.name());
        hobbyGroup.setEmail(request.email());
        hobbyGroup.setPassword(request.password());
        hobbyGroup.setInterest(request.interest());

        HobbyGroup savedHobbyGroup = hobbyGroupRepository.save(hobbyGroup);
        return HobbyGroupResponse.from(savedHobbyGroup);
    }

    @Transactional(readOnly = true)
    public HobbyGroupResponse getProfile(Long hobbyGroupId) {
        return HobbyGroupResponse.from(findHobbyGroup(hobbyGroupId));
    }

    @Transactional(readOnly = true)
    public List<HobbyGroupResponse> browse(Interest category) {
        List<HobbyGroup> hobbyGroups = category == null
                ? hobbyGroupRepository.findAll()
                : hobbyGroupRepository.findMatchingCategory(category);
        return hobbyGroups.stream()
                .map(HobbyGroupResponse::from)
                .toList();
    }

    public HobbyGroupResponse updateProfile(Long hobbyGroupId, HobbyGroupUpdateRequest request) {
        HobbyGroup hobbyGroup = findHobbyGroup(hobbyGroupId);
        if (!hobbyGroup.getEmail().equalsIgnoreCase(request.email())
                && hobbyGroupRepository.existsByEmailIgnoreCase(request.email())) {
            throw new ConflictException("An account with that email already exists.");
        }
        hobbyGroup.setName(request.name());
        hobbyGroup.setEmail(request.email());
        hobbyGroup.setInterest(request.interest());
        return HobbyGroupResponse.from(hobbyGroup);
    }

    HobbyGroup findHobbyGroup(Long hobbyGroupId) {
        return hobbyGroupRepository.findById(hobbyGroupId)
                .orElseThrow(() -> new NotFoundException("Hobby group " + hobbyGroupId + " was not found."));
    }

    private void ensureEmailAvailable(String email) {
        if (hobbyGroupRepository.existsByEmailIgnoreCase(email)) {
            throw new ConflictException("An account with that email already exists.");
        }
    }
}
