package com.projeto.gestao.repository;

import com.projeto.gestao.entity.Order;
import com.projeto.gestao.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUserId(Long userId);

    List<Order> findByStatus(OrderStatus status);
}
