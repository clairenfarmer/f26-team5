package com.csc340.hobbycircles.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.csc340.hobbycircles.service.IndividualService;
import jakarta.validation.Valid;
import com.csc340.hobbycircles.service.EventService;
import com.csc340.hobbycircles.dto.CommentRequest;
import com.csc340.hobbycircles.dto.CommentResponse;
import com.csc340.hobbycircles.dto.RsvpResponse;
import com.csc340.hobbycircles.dto.IndividualRegistrationRequest;
import com.csc340.hobbycircles.dto.IndividualResponse;
import com.csc340.hobbycircles.dto.IndividualUpdateRequest;
import com.csc340.hobbycircles.dto.RsvpEventRequest;
import com.csc340.hobbycircles.service.CommentService;
import com.csc340.hobbycircles.service.RsvpService;
import com.csc340.hobbycircles.service.HobbyGroupService;


@RestController 
@RequestMapping("/api/individuals")
public class IndividualController {
    private final IndividualService individualService;
    private final CommentService commentService;
    private final RsvpService rsvpService;

    public IndividualController(
            IndividualService individualService,
            EventService eventService,
            CommentService commentService,
            RsvpService rsvpService,
            HobbyGroupService hobbyGroupService) {
        this.individualService = individualService;
        this.commentService = commentService;
        this.rsvpService = rsvpService;
    }

    //creating profile
    @PostMapping
    public ResponseEntity<IndividualResponse> register(
            @Valid @RequestBody IndividualRegistrationRequest request) {
        IndividualResponse individual = individualService.register(request);
        return ResponseEntity.created(URI.create("/api/individuals/" + individual.id())).body(individual);
    }

    //seeing profile
    @GetMapping("/{individualId}")
    public IndividualResponse getProfile(@PathVariable Long individualId) {
        return individualService.getProfile(individualId);
    }

    //updating individual profile
    @PutMapping("/{individualId}")
    public IndividualResponse updateProfile(
            @PathVariable Long individualId,
            @Valid @RequestBody IndividualUpdateRequest request) {
        return individualService.updateProfile(individualId, request);
    }

    // seeing rsvps created 
    @GetMapping("/{individualId}/rsvps")
    public List<RsvpResponse> rsvps(@PathVariable Long individualId) {
        return rsvpService.listForIndividual(individualId);
    }

    // rsvp to an event
    @PostMapping("/{individualId}/rsvps")
    public RsvpResponse rsvpEvent(
            @PathVariable Long individualId,
            @Valid @RequestBody RsvpEventRequest request) {
        return rsvpService.create(individualId, request);
    }
    
    // all comments made by an individual
    @GetMapping("/{individualId}/comments")
    public List<CommentResponse> reviews(@PathVariable Long individualId) {
        return commentService.listForIndividual(individualId);
    }

    // comment on hobbyGroup
    @PostMapping("/{individualId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long individualId,
            @RequestParam Long hobbyGroupId,
            @Valid @RequestBody CommentRequest request) {
        CommentResponse created = commentService.create(individualId, hobbyGroupId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
