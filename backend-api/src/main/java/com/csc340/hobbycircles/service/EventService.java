package com.csc340.hobbycircles.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csc340.hobbycircles.dto.EventRequest;
import com.csc340.hobbycircles.dto.EventResponse;
import com.csc340.hobbycircles.dto.EventUpdateRequest;
import com.csc340.hobbycircles.entity.Event;
import com.csc340.hobbycircles.entity.HobbyGroup;
import com.csc340.hobbycircles.entity.Interest;
import com.csc340.hobbycircles.repository.EventRepository;
import com.csc340.hobbycircles.repository.HobbyGroupRepository;

@Service
@Transactional
public class EventService {
    private final EventRepository eventRepository;
    private final HobbyGroupRepository hobbyGroupRepository;
    
    public EventService(EventRepository eventRepository, HobbyGroupRepository hobbyGroupRepository) {
        this.eventRepository = eventRepository;
        this.hobbyGroupRepository = hobbyGroupRepository;
    }

    public EventResponse create(Long hobbyGroupId, EventRequest request) {
        HobbyGroup hobbyGroup = hobbyGroupRepository.findById(hobbyGroupId)
                .orElseThrow(() -> new NotFoundException("Hobby group " + hobbyGroupId + " was not found."));
        Event event = new Event();
        event.setName(request.name());
        event.setDescription(request.description());
        event.setInterest(request.interest());
        event.setLocation(request.location());
        event.setTime(request.time());
        event.setStatus(request.status());
        event.setHobbygroup(hobbyGroup);

        Event savedEvent = eventRepository.save(event);
        return EventResponse.from(savedEvent);
    }

    @Transactional (readOnly = true)
    public EventResponse getEvent(Long eventId) {
        Event event = findEvent(eventId);
        return EventResponse.from(event);
    }

    @Transactional(readOnly = true)
    public List<EventResponse> browse(Long hobbyGroupId, String status) {
        List<Event> events;
        if (status == null || status.isBlank()) {
            events = eventRepository.findAllByHobbygroupIdOrderById(hobbyGroupId);
        } else {
            events = eventRepository.findAllByHobbygroupIdAndStatusIgnoreCaseOrderById(hobbyGroupId, status.trim());
        }
        return events.stream()
                .map(EventResponse::from)
                .toList();
    }

    public EventResponse updateEvent(Long eventId, EventUpdateRequest request) {
        Event event = findEvent(eventId);
        event.setName(request.name());
        event.setDescription(request.description());
        event.setInterest(request.interest());
        event.setLocation(request.location());
        event.setTime(request.time());
        event.setStatus(request.status());

        return EventResponse.from(event);
    }

    Event findEvent(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Event " + eventId + " was not found."));
    }

    @Transactional (readOnly = true)
    public List<EventResponse> browse(Interest interest){
        List<Event> events = interest == null || interest.toString().isBlank()
                ? eventRepository.findAll()
                : eventRepository.findAllByInterestOrderById(interest);
        return events.stream()
                .map(EventResponse::from)
                .toList();
    }


}
