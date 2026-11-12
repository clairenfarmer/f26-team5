package com.csc340.hobbycircles.dto;

import com.csc340.hobbycircles.entity.Event;

public record RsvpResponse (
    Long id,
    Long eventId,
    Long individualId,
    String individualName,
    String eventName,
    String eventLocation,
    String eventTime,
    String eventStatus
) {
    public static RsvpResponse from(com.csc340.hobbycircles.entity.RSVP rsvp) {
        Event event = rsvp.getEvent();
        return new RsvpResponse(
            rsvp.getId(),
            event.getId(),
            rsvp.getIndividual().getId(),
            rsvp.getIndividual().getName(),
            event.getName(), 
            event.getLocation(),
            event.getTime().toString(),
            event.getStatus()
        );
    }
    
}
