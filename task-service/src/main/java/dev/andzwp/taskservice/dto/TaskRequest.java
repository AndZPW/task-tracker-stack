package dev.andzwp.taskservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TaskRequest(@NotBlank String header, @NotBlank String content, @Min(1) Long userId) {
}
