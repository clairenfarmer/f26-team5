package com.csc340.hobbycircles.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csc340.hobbycircles.service.CommentService;
import com.csc340.hobbycircles.service.EventService;
import com.csc340.hobbycircles.service.HobbyGroupService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.csc340.hobbycircles.dto.HobbyGroupRegistrationRequest;
import com.csc340.hobbycircles.dto.HobbyGroupResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.csc340.hobbycircles.dto.EventRequest;
import com.csc340.hobbycircles.dto.EventResponse;
import com.csc340.hobbycircles.dto.EventUpdateRequest;
import com.csc340.hobbycircles.dto.HobbyGroupUpdateRequest;
import com.csc340.hobbycircles.dto.RsvpResponse;
import com.csc340.hobbycircles.entity.Interest;
import com.csc340.hobbycircles.dto.CommentResponse;
import com.csc340.hobbycircles.dto.CommentReplyRequest;
import com.csc340.hobbycircles.service.RsvpService;

@RestController 
@RequestMapping ("/api/hobbygroups")
public class HobbyGroupController {
    private final HobbyGroupService hobbyGroupService;
    private final EventService eventService;
    private final RsvpService rsvpService;
    private final CommentService commentService;

    // hobby group
    public HobbyGroupController(HobbyGroupService hobbyGroupService, EventService eventService, RsvpService rsvpService, CommentService commentService) {
        this.hobbyGroupService = hobbyGroupService;
        this.eventService = eventService;
        this.rsvpService = rsvpService;
        this.commentService = commentService;
    }

    // HobbyGroup profile endpoints
    @PostMapping
    public ResponseEntity<HobbyGroupResponse> register(
            @Valid @RequestBody HobbyGroupRegistrationRequest registration) {

        HobbyGroupResponse created = hobbyGroupService.register(registration);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

     @PutMapping("/{hobbyGroupId}")
    public ResponseEntity<HobbyGroupResponse> updateProfile(
            @PathVariable Long hobbyGroupId,
            @Valid @RequestBody HobbyGroupUpdateRequest updateRequest) {

        HobbyGroupResponse updated = hobbyGroupService.updateProfile(hobbyGroupId, updateRequest);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{hobbyGroupId}")
    public ResponseEntity<HobbyGroupResponse> viewProfile(
            @PathVariable Long hobbyGroupId) {

        HobbyGroupResponse group = hobbyGroupService.getProfile(hobbyGroupId);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    public ResponseEntity<List<HobbyGroupResponse>> browseGroups(
            @RequestParam(required = false) Interest interest) {
                
        List<HobbyGroupResponse> groups = hobbyGroupService.browse(interest);
        return ResponseEntity.ok(groups);
    }

    // Event endpoints
    @PostMapping("/{hobbyGroupId}/events")
    public ResponseEntity<EventResponse> createEvent(
            @PathVariable Long hobbyGroupId,
            @Valid @RequestBody EventRequest eventCreate) {

        EventResponse created = eventService.create(hobbyGroupId, eventCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{hobbyGroupId}/events/{eventId}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable Long hobbyGroupId,
            @PathVariable Long eventId,
            @Valid @RequestBody EventUpdateRequest request) {

        EventResponse updated = eventService.updateEvent(hobbyGroupId, eventId, request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{hobbyGroupId}/events")
    public ResponseEntity<List<EventResponse>> browseEvents(
            @PathVariable Long hobbyGroupId) {

        List<EventResponse> events = eventService.browse(hobbyGroupId, null);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{hobbyGroupId}/events/{eventId}")
    public ResponseEntity<EventResponse> viewEvent(
            @PathVariable Long hobbyGroupId,
            @PathVariable Long eventId) {

        EventResponse event = eventService.getEvent(eventId);
        return ResponseEntity.ok(event);
    }

    // rsvp endpoint
    @GetMapping("/{hobbyGroupId}/events/{eventId}/rsvps")
    public ResponseEntity<List<RsvpResponse>> viewRsvps(
            @PathVariable Long hobbyGroupId,
            @PathVariable Long eventId) {

        List<RsvpResponse> rsvps = rsvpService.getRsvps(hobbyGroupId, eventId);
        return ResponseEntity.ok(rsvps);
    }

    // comment endpoints
     @GetMapping("/{hobbyGroupId}/comments")
    public ResponseEntity<List<CommentResponse>> viewComments(
            @PathVariable Long hobbyGroupId) {

        List<CommentResponse> comments = commentService.listForHobbyGroup(hobbyGroupId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{hobbyGroupId}/comments/{commentId}/replies")
    public ResponseEntity<CommentResponse> replyToComment(
            @PathVariable Long hobbyGroupId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentReplyRequest reply) {

        CommentResponse commentCreated = commentService.reply(hobbyGroupId, commentId, reply);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentCreated);
    }
}
