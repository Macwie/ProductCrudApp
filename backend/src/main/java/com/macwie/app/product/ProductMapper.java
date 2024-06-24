package com.macwie.app.product;

import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
interface ProductMapper {
    
    ProductDTO toDto(Product product);

    Product toEntity(ProductDTO dto);

}
