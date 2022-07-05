package com.example.demo.service;

import com.example.demo.dto.AddOrderItemDTO;
import com.example.demo.dto.CreateOrderDTO;
import com.example.demo.dto.RemoveItemDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<Order> findAll(Pageable pageable);
    Order findOrderById(Long orderId);
    Order createOrder(CreateOrderDTO createOrderDTO);
    void removeOrderByStatus(Order order);
    Order addOrderItem(Long orderId, AddOrderItemDTO addOrderItemDTO);

    Order removeOrderItem( RemoveItemDTO removeItemDTO);

    Order updateOrderStatusPaid(Long id, OrderStatus orderStatus);
    Order updateOrderStatusCancel(Long id,OrderStatus orderStatus);

    Page<Order> findOrdersByOrderStatus(OrderStatus orderStatus,Pageable pageable);
}
