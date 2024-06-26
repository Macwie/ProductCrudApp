package com.macwie.app.product;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,

        String name,
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        BigDecimal price) {
}
