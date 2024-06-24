package com.macwie.app.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
class ProductController {

    private final ProductService productService;

    @GetMapping
    ResponseEntity<List<ProductDTO>> getAllProducts() {
        log.info("Request for getting all products");
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        log.info("Request for getting product with id: {}", id);
        return ResponseEntity.of(productService.getProduct(id));
    }

    @PostMapping
    ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid ProductDTO product) {
        log.info("Request for adding new product: {}", product);
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("Request for deleting product with id: {}", id);
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
