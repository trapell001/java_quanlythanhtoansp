package com.example.demo.mapper;

import com.example.demo.dto.CreateOrderDTO;
import com.example.demo.entity.Order;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CreatOrderMapper extends Mapper<CreateOrderDTO, Order> {
}
