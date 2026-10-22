package com.csc340.hobbycircles.dto;

public record RsvpResponse (
    Long id,
    Long eventId,
    Long individualId,
    String individualName
) {
    public static RsvpResponse from(com.csc340.hobbycircles.entity.RSVP rsvp) {
        return new RsvpResponse(
            rsvp.getId(),
            rsvp.getEvent().getId(),
            rsvp.getIndividual().getId(),
            rsvp.getIndividual().getName()
        );
    }
    
}
