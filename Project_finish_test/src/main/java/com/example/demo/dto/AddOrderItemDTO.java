package com.example.demo.dto;

import lombok.Data;
@Data
public class AddOrderItemDTO {
    private Long productId;
    private Integer quantity;
    private Double amount;
}
