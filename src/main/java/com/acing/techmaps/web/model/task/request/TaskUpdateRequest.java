package com.acing.techmaps.web.model.task.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TaskUpdateRequest(
        @NotBlank String title,
        @NotBlank String description,
        @Min(0) int position
) {
}