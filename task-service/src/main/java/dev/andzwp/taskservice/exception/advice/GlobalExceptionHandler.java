package dev.andzwp.taskservice.exception.advice;

import dev.andzwp.taskservice.dto.ErrorResponse;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @Nullable HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var response = ErrorResponse.builder()
                .statusCode(HttpStatus.valueOf(status.value()))
                .path(request.getContextPath())
                .message(ex.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        var response = ErrorResponse.builder()
                .statusCode(status)
                .time(LocalDateTime.now())
                .message(ex.getMessage())
                .path(request.getContextPath())
                .build();

        return new ResponseEntity<>(response, status);
    }



}
