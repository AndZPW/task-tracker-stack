package dev.andzwp.taskservice.dto;

import org.springframework.http.HttpStatus;

public record Response(HttpStatus status,String message) {
}
