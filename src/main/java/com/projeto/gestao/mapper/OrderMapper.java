package com.projeto.gestao.mapper;

import com.projeto.gestao.dto.OrderItemResponseDTO;
import com.projeto.gestao.dto.OrderResponseDTO;
import com.projeto.gestao.entity.Order;
import com.projeto.gestao.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class OrderMapper {
    private static OrderItemResponseDTO toItemDto(OrderItem item){
        BigDecimal subtotal = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

        return new OrderItemResponseDTO(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getUnitPrice(),
                subtotal);
    }

    public static OrderResponseDTO toDto(Order order){

        List<OrderItemResponseDTO> items = order.getOrderItem().stream().map(OrderMapper::toItemDto).toList();

        return new OrderResponseDTO(
                order.getId(),
                order.getUser().getId(),
                order.getStatus(),
                order.getTotal(),
                order.getCreatedAt(),
                items);

    }
}
