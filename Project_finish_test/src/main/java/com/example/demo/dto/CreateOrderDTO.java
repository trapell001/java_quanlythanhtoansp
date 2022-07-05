package com.example.demo.dto;

import lombok.Data;

@Data
public class CreateOrderDTO {
    private Long orderId;
    private Long productId;
    private Long quantity;
}
