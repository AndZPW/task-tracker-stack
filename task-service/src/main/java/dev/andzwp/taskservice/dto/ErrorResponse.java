package dev.andzwp.taskservice.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(String path, HttpStatus statusCode, String message, LocalDateTime time) {
}
