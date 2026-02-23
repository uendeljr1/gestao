package com.projeto.gestao.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductRequestDTO {

    @NotBlank(message = "Nomé e obrigatório")
    private String name;

    @Size(max = 500 , message = "Descrição deve ter no máximo 500 caracteres")
    private String description;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0",inclusive = false,message = "Preço deve ser maior que zero")
    private BigDecimal price;

    @NotNull(message = "Estoque é obrigatório")
    @Min(value = 0,message = "Estoque não pode ser negativo")
    private Integer stock;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String name, String description, BigDecimal price, Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }
}
