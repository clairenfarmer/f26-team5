package com.csc340.hobbycircles.dto;

import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import com.csc340.hobbycircles.entity.Interest;
import jakarta.validation.constraints.NotNull;

public record RsvpEventRequest(
        @NotNull Long eventId,
        @Size(max = 2000) String description,
        @NotNull LocalDateTime time,
        @NotNull Interest interest,
        @Size(max = 255) String location) {
}
