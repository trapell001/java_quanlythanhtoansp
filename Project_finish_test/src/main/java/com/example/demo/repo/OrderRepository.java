package com.example.demo.repo;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;


public interface OrderRepository extends BaseRepository<Order,Long> {
    Page<Order> findOrderByStatus(OrderStatus orderStatus, Pageable pageable);

}
