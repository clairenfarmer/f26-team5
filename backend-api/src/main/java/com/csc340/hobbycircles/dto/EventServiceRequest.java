package com.csc340.hobbycircles.dto;

import java.time.LocalDateTime;

import com.csc340.hobbycircles.entity.Interest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record EventServiceRequest (
    @NotBlank 
    @Size(max = 150) 
    String name,

    @Size(max = 2000)
    String description,

    @NotNull
    Interest interest,

    @Size(max = 255)
    String location,

    @NotNull
    LocalDateTime time,

    @Size(max = 30)
    String status) {

    }

