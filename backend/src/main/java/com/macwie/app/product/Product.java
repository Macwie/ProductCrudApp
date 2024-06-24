package com.macwie.app.product;

import com.macwie.app.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")
class Product extends AbstractEntity {

    @NotEmpty
    private String name;

    @NotNull
    private BigDecimal price;

}
