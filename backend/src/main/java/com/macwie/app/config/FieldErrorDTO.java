package com.macwie.app.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class FieldErrorDTO {

    private String field;
    private String error;
}
