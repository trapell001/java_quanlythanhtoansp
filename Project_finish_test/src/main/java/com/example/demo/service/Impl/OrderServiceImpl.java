package com.example.demo.service.Impl;

import com.example.demo.dto.AddOrderItemDTO;
import com.example.demo.dto.CreateOrderDTO;
import com.example.demo.dto.RemoveItemDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.Product;
import com.example.demo.exeption.OrderNotFoundException;
import com.example.demo.mapper.AddOrderItemMapper;
import com.example.demo.mapper.CreatOrderMapper;
import com.example.demo.repo.OrderItemRepository;
import com.example.demo.repo.OrderRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderItemRepository orderItemRepository;
    private OrderRepository orderRepository;
    private CreatOrderMapper creatOrderMapper;
    private ProductService productService;
    private AddOrderItemMapper addOrderItemMapper;
    @Autowired
    public OrderServiceImpl(OrderItemRepository orderItemRepository, OrderRepository orderRepository, CreatOrderMapper creatOrderMapper, ProductService productService, AddOrderItemMapper addOrderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.creatOrderMapper = creatOrderMapper;
        this.productService = productService;
        this.addOrderItemMapper = addOrderItemMapper;
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return  orderRepository.findAll(pageable.previousOrFirst());
    }

    @Override
    public Order findOrderById(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        order.orElseThrow(()->new OrderNotFoundException());
        return order.get();
    }

    @Override
    public Order createOrder(CreateOrderDTO createOrderDTO) {
        Order order = creatOrderMapper.from(createOrderDTO);
        return orderRepository.save(order);
    }

    @Override
    public void removeOrderByStatus(Order order) {
        if (order.getStatus().equals(OrderStatus.CREATED)||order.getStatus().equals(OrderStatus.CANCELLED))
            orderRepository.delete(order);
    }

    @Override
    public Order addOrderItem(Long id, AddOrderItemDTO addOrderItemDTO) {
        Order order =orderRepository.findById(id).get();
        OrderItem orderItem = addOrderItemMapper.from(addOrderItemDTO);
        if(order.getStatus().equals(OrderStatus.CREATED)){
            order.getOrderItems().add(orderItem);
            order.setTotalAmount(order.getTotalAmount() + (addOrderItemDTO.getAmount() * addOrderItemDTO.getQuantity()));
        }
        orderRepository.save(order);
        Product product = productService.findProductById(addOrderItemDTO.getProductId());
        product.setAvaiable(product.getAvaiable() - addOrderItemDTO.getQuantity());
        productService.updateProduct(product);
        return order;
    }

    @Override
    public Order removeOrderItem( RemoveItemDTO removeItemDTO) {
        Order order =orderRepository.findById(removeItemDTO.getOrderId()).get();
        OrderItem orderItem = orderItemRepository.findById(removeItemDTO.getOrderItemId()).get();
        if(order.getStatus().equals(OrderStatus.CREATED)){
            order.getOrderItems().remove(orderItem);
            order.setTotalAmount(order.getTotalAmount() - (orderItem.getAmount() * orderItem.getQuantity()));
        }
        orderRepository.save(order);
        Product product = productService.findProductById(orderItem.getProduct().getId());
        product.setAvaiable(product.getAvaiable() + orderItem.getQuantity());
        productService.updateProduct(product);
        return order;
    }

    @Override
    public Order updateOrderStatusPaid(Long id,OrderStatus orderStatus) {
        Order order = orderRepository.findById(id).get();
        if(order.getStatus().equals(orderStatus.CREATED)){
            order.setStatus(OrderStatus.PAID);
        }
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order updateOrderStatusCancel(Long id,OrderStatus orderStatus) {
        Order order = orderRepository.findById(id).get();
        if(order.getStatus().equals(orderStatus.CREATED)){
            order.setStatus(OrderStatus.CANCELLED);
        }
        orderRepository.save(order);
        return order;
    }

    @Override
    public Page<Order> findOrdersByOrderStatus(OrderStatus orderStatus,Pageable pageable) {
        return orderRepository.findOrderByStatus(orderStatus,pageable.previousOrFirst());
    }
}
