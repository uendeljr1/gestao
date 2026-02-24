package com.projeto.gestao.service;

import com.projeto.gestao.dto.ProductRequestDTO;
import com.projeto.gestao.dto.ProductResponseDTO;
import com.projeto.gestao.entity.Product;
import com.projeto.gestao.exception.BusinessException;
import com.projeto.gestao.exception.ResourceNotFoundException;
import com.projeto.gestao.mapper.ProductMapper;
import com.projeto.gestao.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponseDTO create(ProductRequestDTO dto){
        if (dto.getPrice().compareTo(BigDecimal.ZERO) < 0){
            throw new BusinessException("Preço não pode ser negativo");
        }

        if (dto.getStock() < 0){
            throw new BusinessException("Estoque não pode ser negativo");
        }

        Product product = ProductMapper.toEntity(dto);

        return ProductMapper.toDto(repository.save(product));
    }

    public List<ProductResponseDTO> findAll(){
        return repository.findAll().stream().map(ProductMapper::toDto).toList();
    }

    public ProductResponseDTO findById(Long id){
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado com id: " + id));

        return ProductMapper.toDto(product);
    }

    public ProductResponseDTO upade(Long id ,ProductRequestDTO dto){
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado com id: " + id));

        if (dto.getPrice().compareTo(BigDecimal.ZERO) < 0 ){
            throw new BusinessException("Preço não pode ser negativo");
        }

        if (dto.getStock() < 0){
            throw new BusinessException("Estoque não pode ser negativo");
        }
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        return ProductMapper.toDto(repository.save(product));
    }

    public void deleteById(Long id){
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado com id: " + id));

        repository.deleteById(id);
    }

}
