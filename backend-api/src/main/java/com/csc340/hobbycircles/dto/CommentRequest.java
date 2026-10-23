package com.csc340.hobbycircles.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentRequest(@NotBlank @Size(max = 2000) String message, @NotNull Long hobbyGroupId) {
}
