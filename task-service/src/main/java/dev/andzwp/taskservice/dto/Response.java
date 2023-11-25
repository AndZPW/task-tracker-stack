package dev.andzwp.taskservice.dto;

import org.springframework.http.HttpStatus;

public record Response(HttpStatus statusCode, String message) {
}
