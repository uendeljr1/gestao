package com.projeto.gestao.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemRequestDTO {

    @NotNull(message = "ProductId é obrigatório")
    private Long productId;

    @NotNull(message = "Quantidade e obrigatório")
    @Min(value = 1,message = "Quantidade deve ser no mínimo 1")
    private Integer quantity;

    public OrderItemRequestDTO() {
    }

    public OrderItemRequestDTO(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
