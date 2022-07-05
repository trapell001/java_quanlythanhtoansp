package com.example.demo.service.Impl;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.exeption.CustomerNotFoundException;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.repo.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private  CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }
    @Override
    public Page<CustomerDTO> findAll(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable.previousOrFirst());
        return customers.map(customerMapper::to);
    }

    @Override
    public CustomerDTO findCustomerById(Long customerId) {
        Optional<Customer> customer= customerRepository.findById(customerId);
        customer.orElseThrow(() -> new CustomerNotFoundException());
        return customerMapper.to(customer.get());
    }
    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.from(customerDTO);
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomerById(Long id,CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).get();
        customer.setAddress(customerDTO.getAddress());
        customer.setName(customerDTO.getName());
        customer.setMobile(customerDTO.getMobile());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
