package dev.andzwp.taskservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TaskDTO(@NotBlank String header, @NotBlank String content, @NotBlank Status status) {
}
