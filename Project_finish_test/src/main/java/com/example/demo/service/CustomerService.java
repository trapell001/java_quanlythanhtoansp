package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<CustomerDTO> findAll(Pageable pageable);
    CustomerDTO findCustomerById(Long customerId);
    Customer createCustomer(CustomerDTO customerDTO);
    Customer updateCustomerById(Long id, CustomerDTO customerDTO);
    void deleteCustomerById(Long customerId);
}
