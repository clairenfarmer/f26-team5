package com.csc340.hobbycircles.service;

import com.csc340.hobbycircles.dto.RsvpEventRequest;
import com.csc340.hobbycircles.dto.RsvpResponse;
import com.csc340.hobbycircles.entity.Event;
import com.csc340.hobbycircles.entity.RSVP;
import com.csc340.hobbycircles.entity.Individual;
import com.csc340.hobbycircles.repository.EventRepository;
import com.csc340.hobbycircles.repository.IndividualRepository;
import com.csc340.hobbycircles.repository.RsvpRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RsvpService {

    private final RsvpRepository rsvpRepository;
    private final EventRepository eventRepository;
    private final IndividualRepository individualRepository;

    public RsvpService(RsvpRepository rsvpRepository, EventRepository eventRepository, IndividualRepository individualRepository) {
        this.rsvpRepository = rsvpRepository;
        this.eventRepository = eventRepository;
        this.individualRepository = individualRepository;
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

    public RsvpResponse create(Long individualId, RsvpEventRequest request) {
        Individual individual = individualRepository.findById(individualId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Individual not found."));

        Event event = eventRepository.findById(request.eventId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event not found."));

        if (rsvpRepository.existsByIndividualIdAndEventId(individualId, event.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Already RSVP'd to this event.");
        }

        RSVP rsvp = new RSVP();
        rsvp.setIndividual(individual);
        rsvp.setEvent(event);
        rsvp.setDescription(request.description());

        return RsvpResponse.from(rsvpRepository.save(rsvp));
    }

    @Transactional(readOnly = true)
    public List<RsvpResponse> listForIndividual(Long individualId) {
        if (!individualRepository.existsById(individualId)) {
            throw new NotFoundException("Individual " + individualId + " was not found.");
        }
        return rsvpRepository.findAllByIndividualId(individualId)
                .stream().map(RsvpResponse::from).toList();
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
