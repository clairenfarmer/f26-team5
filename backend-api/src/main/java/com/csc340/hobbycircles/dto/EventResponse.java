package com.csc340.hobbycircles.dto;

import com.csc340.hobbycircles.entity.Event;

public record EventResponse(
    Long id,
    Long hobbygroupId,
    String name,
    String description,
    String location,
    String time,
    String status,
    int rsvpCount
) {
    public static EventResponse from(Event event) {
        return new EventResponse(
            event.getId(),
            event.getHobbygroup().getId(),
            event.getName(),
            event.getDescription(),
            event.getLocation(),
            event.getTime().toString(),
            event.getStatus(),
            event.getRsvps() == null ? 0 : event.getRsvps().size()
        );
    }
}
