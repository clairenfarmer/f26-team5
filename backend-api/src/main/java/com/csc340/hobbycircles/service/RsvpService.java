package com.csc340.hobbycircles.service;

import com.csc340.hobbycircles.dto.RsvpResponse;
import com.csc340.hobbycircles.entity.Event;
import com.csc340.hobbycircles.entity.RSVP;
import com.csc340.hobbycircles.repository.EventRepository;
import com.csc340.hobbycircles.repository.RsvpRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RsvpService {

    private final RsvpRepository rsvpRepository;
    private final EventRepository eventRepository;

    public RsvpService(RsvpRepository rsvpRepository, EventRepository eventRepository) {
        this.rsvpRepository = rsvpRepository;
        this.eventRepository = eventRepository;
    }

    public List<RsvpResponse> getRsvps(Long hobbyGroupId, Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event not found."));

        if (!event.getHobbygroup().getId().equals(hobbyGroupId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Event does not belong to this hobby group.");
        }

        return rsvpRepository.findAllByEventId(eventId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private RsvpResponse toResponse(RSVP rsvp) {
        return new RsvpResponse(
                rsvp.getId(),
                rsvp.getEvent().getId(),
                rsvp.getIndividual().getId(),
                rsvp.getIndividual().getName()
        );
    }
}
