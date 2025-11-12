package com.sparta.paymentsystem.repository;

import com.sparta.paymentsystem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderId(String orderId);

    List<OrderItem> findByProductId(Long productId);
}