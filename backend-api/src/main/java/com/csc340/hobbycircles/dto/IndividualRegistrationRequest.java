package com.csc340.hobbycircles.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import com.csc340.hobbycircles.entity.Interest;

public record IndividualRegistrationRequest(
                @NotBlank @Size(max = 100) String name,
                @NotBlank @Email @Size(max = 254) String email,
                @NotBlank @Size(min = 8, max = 72) String password,
                @NotNull Interest interest) {
}
