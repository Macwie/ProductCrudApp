package com.macwie.app.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Optional<ProductDTO> getProduct(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto);
    }

    @Override
    public List<ProductDTO> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDTO addProduct(ProductDTO dto) {
        Product product = productMapper.toEntity(dto);
        productRepository.save(product);

        return productMapper.toDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
