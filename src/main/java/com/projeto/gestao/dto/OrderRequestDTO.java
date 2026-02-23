package com.projeto.gestao.dto;

import com.projeto.gestao.entity.OrderItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderRequestDTO {

    @NotNull(message = "UserId é obrigatório")
    private Long userId;

    @NotEmpty(message = "Pedido de conter pelo menos um item")
    @Valid
    private List<OrderItemRequestDTO> orders;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(Long userId, List<OrderItemRequestDTO> orders) {
        this.userId = userId;
        this.orders = orders;
    }

    public Long getUserId() {
        return userId;
    }

    public List<OrderItemRequestDTO> getOrders() {
        return orders;
    }
}
