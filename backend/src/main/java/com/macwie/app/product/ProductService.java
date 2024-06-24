package com.macwie.app.product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<ProductDTO> getProduct(Long id);

    List<ProductDTO> getProducts();

    ProductDTO addProduct(ProductDTO dto);

    void deleteProduct(Long id);

}
