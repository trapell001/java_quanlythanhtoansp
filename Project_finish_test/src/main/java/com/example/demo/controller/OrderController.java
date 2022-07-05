package com.example.demo.controller;

import com.example.demo.dto.AddOrderItemDTO;
import com.example.demo.dto.CreateOrderDTO;
import com.example.demo.dto.RemoveItemDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Enumerated;

@RestController
@RequestMapping("/order")
public class OrderController {
    private  OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/")
    public ResponseEntity<Page<Order>> findAll(@RequestParam int index , @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(index,size);
        Page<Order> orders = orderService.findAll(pageRequest);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable Long id){
        Order order = orderService.findOrderById(id);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CreateOrderDTO> createOrder(@RequestBody CreateOrderDTO createOrderDTO){
        orderService.createOrder(createOrderDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteOrderById(@PathVariable Long id){
        Order order = orderService.findOrderById(id);
        orderService.removeOrderByStatus(order);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    @PutMapping("/addOrderItem")
    public ResponseEntity<Order> addOrderItem(@PathVariable Long id , @RequestBody AddOrderItemDTO addOrderItemDTO){
        addOrderItem(id,addOrderItemDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/removeOrderItem")
    public ResponseEntity removeOrderItem(@RequestBody RemoveItemDTO removeItemDTO){
        orderService.removeOrderItem(removeItemDTO);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    @PostMapping("/paid/{id}")
    public ResponseEntity<Order> updateOrderStatusPaid(@PathVariable Long id, @PathVariable String orderStatus){
        orderService.updateOrderStatusPaid(id,OrderStatus.valueOf(orderStatus));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PostMapping("/cancel/{id}")
    public ResponseEntity<Order> updateOrderStatusCancel(@PathVariable Long id, @PathVariable String orderStatus){
        orderService.updateOrderStatusCancel(id,OrderStatus.valueOf(orderStatus));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
