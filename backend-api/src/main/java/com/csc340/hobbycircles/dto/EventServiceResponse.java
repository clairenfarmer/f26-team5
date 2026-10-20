package com.csc340.hobbycircles.dto;

import com.csc340.hobbycircles.entity.Event;

public record EventServiceResponse(
    Long id,
    String name,
    String description,
    String location,
    String time,
    String status,
    Long hobbygroupId,
    Long rsvpId
) {
    public static EventServiceResponse fromEvent(Event event) {
        return new EventServiceResponse(
            event.getId(),
            event.getName(),
            event.getDescription(),
            event.getLocation(),
            event.getTime().toString(),
            event.getStatus(),
            event.getHobbygroup().getId(),
            event.getRsvp().getId()
        );
    }
}
