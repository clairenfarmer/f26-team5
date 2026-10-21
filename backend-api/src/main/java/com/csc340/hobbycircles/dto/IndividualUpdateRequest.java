package com.csc340.hobbycircles.dto;

import com.csc340.hobbycircles.entity.Interest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record IndividualUpdateRequest(
            @NotBlank @Size(max = 100) String name,
            @NotBlank @Email @Size(max = 254) String email,
            @NotNull Interest interest) {
}
