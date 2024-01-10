package dev.andzwp.taskservice.exception;

import dev.andzwp.taskservice.dto.ErrorResponse;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @Nullable HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var response = ErrorResponse.builder()
                .statusCode(HttpStatus.valueOf(status.value()))
                .path(request.getContextPath())
                .message(ex.getMessage())
                .time(LocalDateTime.now())
                .build();
        log.warn("The following exception has thrown MethodArgumentNotValidException with message {}", ex.getMessage());
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

        log.warn("The following exception has thrown Exception with message {}", ex.getMessage());
        return new ResponseEntity<>(response, status);
    }


    @ExceptionHandler(IdOutOfBoundsException.class)
    public ResponseEntity<ErrorResponse> handleIdOutOfBoundsException(IdOutOfBoundsException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;

        var response = ErrorResponse.builder()
                .statusCode(status)
                .time(LocalDateTime.now())
                .message(ex.getMessage())
                .path(request.getContextPath())
                .build();

        log.warn("The following exception has thrown IdOutOfBoundsException with message {}", ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(NoSuchTaskException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchTaskException(NoSuchTaskException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;

        var response = ErrorResponse.builder()
                .statusCode(status)
                .time(LocalDateTime.now())
                .message(ex.getMessage())
                .path(request.getContextPath())
                .build();

        log.warn("The following exception has thrown NoSuchTaskException with message {}", ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

}
