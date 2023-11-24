package dev.andzwp.taskservice.dto;

import lombok.Builder;

@Builder
public record TaskRequest(String header, String content, Long userId) {
}
