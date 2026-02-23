package com.projeto.gestao.service;

import com.projeto.gestao.dto.OrderItemRequestDTO;
import com.projeto.gestao.dto.OrderRequestDTO;
import com.projeto.gestao.dto.OrderResponseDTO;
import com.projeto.gestao.entity.*;
import com.projeto.gestao.mapper.OrderMapper;
import com.projeto.gestao.repository.OrderRepository;
import com.projeto.gestao.repository.ProductRepository;
import com.projeto.gestao.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO dto){
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequestDTO itemDto : dto.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Produto nao encontrado"));

            if (product.getStock() < itemDto.getQuantity()){
                throw new RuntimeException("Estoque insuficiente para o produto " + product.getName());
            }

            product.setStock(product.getStock() - itemDto.getQuantity());

            BigDecimal unitPrice = product.getPrice();
            BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(itemDto.getQuantity()));

            OrderItem item = new OrderItem(order,product, itemDto.getQuantity(), unitPrice);

            items.add(item);
            total = total.add(subtotal);
        }
        order.setOrderItem(items);
        order.setTotal(total);


        return OrderMapper.toDto(orderRepository.save(order));
        }

}
