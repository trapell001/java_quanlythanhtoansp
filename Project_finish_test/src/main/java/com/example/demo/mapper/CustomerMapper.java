package com.example.demo.mapper;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CustomerMapper extends Mapper<CustomerDTO,Customer> {
}
