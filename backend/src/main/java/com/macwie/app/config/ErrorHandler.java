package com.macwie.app.config;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@ControllerAdvice
class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorDTO dto = new ErrorDTO(HttpStatus.BAD_REQUEST);

        List<FieldErrorDTO> fieldErrors = Stream.ofNullable(ex.getConstraintViolations())
                .flatMap(Collection::stream)
                .map(error -> new FieldErrorDTO(error.getPropertyPath().toString(), error.getMessage()))
                .toList();

        dto.setFieldErrors(fieldErrors);
        return new ResponseEntity<>(dto, dto.getStatus());
    }

    @ExceptionHandler({Exception.class})
    ResponseEntity<ErrorDTO> handleAll(Exception ex) {
        ErrorDTO dto = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(dto, dto.getStatus());
    }

}
