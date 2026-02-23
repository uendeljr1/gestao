package com.projeto.gestao.repository;

import com.projeto.gestao.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByPriceGreaterThan(BigDecimal price);
}
