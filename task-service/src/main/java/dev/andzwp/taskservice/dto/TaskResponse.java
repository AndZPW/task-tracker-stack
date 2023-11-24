package dev.andzwp.taskservice.dto;

import java.time.LocalDateTime;

public record TaskResponse(Long id, String header, String content, Status status, LocalDateTime time) {
}
