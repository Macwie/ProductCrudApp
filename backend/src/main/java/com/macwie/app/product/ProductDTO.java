package com.macwie.app.product;

import java.math.BigDecimal;

public record ProductDTO(Long id, String name, BigDecimal price) {
}
