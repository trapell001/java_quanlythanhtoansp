package com.example.demo.mapper;

import com.example.demo.dto.AddOrderItemDTO;
import com.example.demo.entity.OrderItem;

@org.mapstruct.Mapper(componentModel = "spring")
public interface AddOrderItemMapper extends Mapper<AddOrderItemDTO, OrderItem> {
}
