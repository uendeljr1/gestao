package com.projeto.gestao.mapper;

import com.projeto.gestao.dto.ProductRequestDTO;
import com.projeto.gestao.dto.ProductResponseDTO;
import com.projeto.gestao.entity.Product;

public class ProductMapper {
    public static Product toEntity(ProductRequestDTO dto){
        return new Product(dto.getName(), dto.getDescription(), dto.getPrice(),dto.getStock());
    }

    public static ProductResponseDTO toDto(Product product){
        return new ProductResponseDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(),product.getStock());
    }
}
