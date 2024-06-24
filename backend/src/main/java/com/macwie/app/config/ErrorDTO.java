package com.macwie.app.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@JsonInclude(Include.NON_EMPTY)
public class ErrorDTO {

    private HttpStatus status;
    private String message;
    private List<FieldErrorDTO> fieldErrors = Lists.newArrayList();

    public ErrorDTO(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorDTO(HttpStatus status) {
        this.status = status;
    }
}
