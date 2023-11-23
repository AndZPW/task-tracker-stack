package dev.andzwp.taskservice.dto;

import lombok.Builder;

@Builder
public record Email(String address, String title, String content) {
}

