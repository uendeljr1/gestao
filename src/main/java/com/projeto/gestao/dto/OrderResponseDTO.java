package com.projeto.gestao.dto;

import com.projeto.gestao.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {
    private Long id;
    private Long userId;
    private OrderStatus status;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private List<OrderItemResponseDTO> items;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(Long id, Long userId, OrderStatus status, BigDecimal total, LocalDateTime createdAt, List<OrderItemResponseDTO> items) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.total = total;
        this.createdAt = createdAt;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderItemResponseDTO> getItems() {
        return items;
    }
}
