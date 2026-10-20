package com.csc340.hobbycircles.dto;

import com.csc340.hobbycircles.entity.Interest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HobbyGroupRegistrationRequest(
    @NotBlank
    @Size(max = 100)
    String name,

    @Email
    @NotBlank
    @Size (max = 254)
    String email,

    @NotBlank
    @Size(min = 8, max = 72)
    String password,

    @NotNull
    Interest interest
) {
}